package br.com.sample.models.daos;

import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import br.com.sample.commons.Paginate;
import br.com.sample.models.daos.base.IBaseDao;

public abstract class ApplicationDao implements IBaseDao {
	
	// Attributes
	protected JdbcTemplate jdbc;
	
	@Autowired
	private Paginate paginator;
	
	Logger logger = Logger.getLogger( getClass().getName() );

	/**
	 * Método que executa o PreparedStatementCreator passado por parametro e retorna o ID do registro gravado no banco. 
	 * @param preparedStatementCreator
	 * @return
	 */
	public Long execute(PreparedStatementCreator preparedStatementCreator) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(preparedStatementCreator, keyHolder);

		return keyHolderValue(keyHolder);
	}
	
	private Long keyHolderValue(KeyHolder key) {
		if (key.getKeys().get("ID") != null)
			return key.getKey().longValue();
		else
			return null;
	}
	
	/**
	 * Executa o SQL passado por parametro e retorna uma lista de objetos encontrados.
	 */
	public List<?> execute(String sql, Object ...args) {
		return jdbc.queryForList(sql, args);
	}
	
	/**
	 * Método para definição da paginação.
	 * @param page
	 * @return
	 */
	public Paginate page(Integer page) {
		return paginator.setDao(this).page(page);
	}

	/*
	 * Getters and Setters
	 */
	
	/**
	 * Método utilizado pelo Spring para injeção de dependência do DataSource.
	 * @param dataSource
	 */
	@Autowired(required=true)
	public void setDataSource(DataSource dataSource) { 
		this.jdbc = new JdbcTemplate(dataSource); 
	}
}
