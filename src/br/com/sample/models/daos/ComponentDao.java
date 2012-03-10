package br.com.sample.models.daos;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import br.com.sample.dtos.Component;
import br.com.sample.environment.QueryReader;
import br.com.sample.models.converters.ComponentConverter;
import br.com.sample.models.daos.interfaces.IComponentDao;

import static br.com.sample.utils.SQLConstants.*;

@Repository(value="component_dao")
public class ComponentDao extends ApplicationDao implements IComponentDao {

	private String appendLikeDescription = " d04.d04_008_c like ? "; 
	private String appendUkey 			 = " d04.ukey = ? ";
	private String computerUkey			 = " d49.ukey = ? ";
	
	private QueryReader getQueryReader() 	 	 {  return new QueryReader("components"); }
	private String getBaseQuery()				 {  return getQueryReader().getQuery("base_query"); }
	private String getComputerComponentsQuery()	 {  return getQueryReader().getQuery("computer_components_query"); }
	
	@Override
	@Cacheable(value = "component_dao")
	public List<Component> searchByName(String name) throws Exception 					{ return searchByName(name, null); }
	@Override
	@Cacheable(value = "component_dao")
	public List<Component> searchByName(String name, Integer page) throws Exception 	{ return searchByName(name, page, null); }
	
	@Override
	@Cacheable(value = "component_dao")
	public List<Component> searchByName(String name, Integer page, Integer per) throws Exception {
		String sql = getBaseQuery() + and + appendLikeDescription;
		return executeAndGetComponents(sql, page, per, "%"+name+"%");
	}
	
	@Override
	@Cacheable(value = "component_dao")
	public Component get(String ukey) throws Exception {
		String sql = getBaseQuery() + and + appendUkey;
		
		return executeAndGetComponent(sql, ukey);
	}

	/**
	 * List dos componentes de um determinado computador (template de máquina)
	 * @param computerUkey
	 * A UKEY do template
	 * @return
	 * List de componentes
	 * @throws Exception
	 * Caso aconteca algum erro de SQL ou de conversão, uma excessão será levantada.
	 */
	@Override
	@Cacheable(value = "component_dao")
	public List<Component> getComputerComponents(String compUkey) throws Exception {
		String sql = getComputerComponentsQuery() + and + computerUkey;
		
		return executeAndGetComponents(sql, null, null, compUkey);
	}
	
	@Override
	@Cacheable(value = "component_dao")
	public Component getComputerComponent(String compUkey, String componentUkey) throws Exception {
		String sql = getComputerComponentsQuery() + and + computerUkey + and + appendUkey;
		
		return executeAndGetComponent(sql, compUkey, componentUkey);
	}
	
	/**
	 * Executa a query passada por parametro e converte o resultado em uma lista de Componentes
	 * 
	 * @param sql
	 * A consulta SQL
	 * @param page
	 * A pagina que deseja (paginação)
	 * @param per
	 * A quantidade de elementos por página (paginação)
	 * @param params
	 * Os parametros a serem preenchidos no SQL (?)
	 * @return
	 * A lista dos componentes
	 * @throws Exception
	 */
	private List<Component> executeAndGetComponents(String sql, Integer page, Integer per, Object ...params) throws Exception {
		List result;
		
		if (page == null)
			result = execute(sql, params);
		else
			result = page(page)
					 .per(per)
					 .execute(sql, params);	
		
		return new ComponentConverter().entitiesToDTOs(result);
	}
	
	/**
	 * Execute a query passada por parametro e converte o resultado para um Componente. Retorna null caso
	 * nenhum resultado seja encontrado.
	 * 
	 * @param sql
	 * A consulta SQL a ser executada
	 * @param params
	 * Os parametros a serem preenchidos no SQL (?)
	 * @return
	 * A componente do banco. Retorna null caso não encontre nenhum elemento.
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private Component executeAndGetComponent(String sql, Object ...params) throws Exception {
		List result = execute(sql, params);
		
		List comps = new ComponentConverter().entitiesToDTOs(result);
		
		if (comps.size() > 0)
			return (Component) comps.get(0);
		else
			return null;
	}

}
