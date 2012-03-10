package br.com.sample.commons.impl;

import java.util.List;

import br.com.sample.commons.Paginate;
import br.com.sample.models.daos.base.IBaseDao;

public class SQLServerPaginate implements Paginate {
	
	private IBaseDao dao = null;
	private Integer page = null;
	private Integer per  = null;

	@Override
	public Paginate per(Integer per) {
		this.per = per;
		return this;
	}

	@Override
	public Paginate page(Integer page) {
		this.page = page;
		return this;
	}
	
	@Override
	public Paginate setDao(IBaseDao dao) {
		this.dao = dao;
		return this;
	}
	
	@Override
	public List<?> execute(String sql, Object... args) {
		String query = buildPaginateQuery(sql, page, per);
		return dao.execute(query, args);
	}
	
	private String buildPaginateQuery(String query, Integer page, Integer per) {
		if (page == null) 	page = 0;
		if (per == null) 	per = 10;
			
		return "select top (:per) t.* from ( :query ) as t where t._row_num > :page".
				replace(":per", per.toString()).
				replace(":page", page.toString()).
				replace(":query", query);
	}
}
