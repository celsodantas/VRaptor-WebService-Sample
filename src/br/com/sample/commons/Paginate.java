package br.com.sample.commons;

import java.util.List;

import br.com.sample.models.daos.base.IBaseDao;

public interface Paginate {

	Paginate per(Integer per);
	Paginate page(Integer per);
	Paginate setDao(IBaseDao dao);
	
	List execute(String sql, Object ...args);
	
}
