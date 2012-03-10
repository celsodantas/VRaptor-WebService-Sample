package br.com.sample.models.daos;

import static br.com.sample.utils.SQLConstants.and;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import br.com.sample.dtos.Computer;
import br.com.sample.environment.QueryReader;
import br.com.sample.models.converters.ComputerConverter;
import br.com.sample.models.daos.interfaces.IComputerDao;

@Repository(value="computer_dao")
public class ComputerDao extends ApplicationDao implements IComputerDao {

	public String ukeyFilter 	= " d49.ukey = ? ";  	  
	
	private QueryReader getQueryReader() 	 {  return new QueryReader("computers"); }
	private String getBaseQuery()	 {  return getQueryReader().getQuery("base_query"); }
	
	@Override
	@Cacheable(value = "computer_dao")
	public List<Computer> all() throws Exception {
		return executeToList( getBaseQuery() );
	}
	
	@Override
	@Cacheable(value = "computer_dao")
	public Computer get(String ukey) throws Exception {
		return executeToSingle( getBaseQuery() + and + ukeyFilter, ukey );
	}
	
	/**********************************************
	 * 
	 * 				 Inside Helpers
	 * 
	 *********************************************/
	
	private Computer executeToSingle(String sql, Object ...params) throws Exception {
		List<?> result = executeToList(sql, params);
		
		if (result.size() > 0)
			return (Computer) result.get(0);
		else
			return null;
	}
	
	private List<Computer> executeToList(String sql, Object ...params) throws Exception {
		List<?> result = execute(sql, params);
		return new ComputerConverter().entitiesToDTOs(result);
	}
}
