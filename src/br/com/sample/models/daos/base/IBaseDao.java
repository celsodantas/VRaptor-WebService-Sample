package br.com.sample.models.daos.base;

import java.util.List;

public interface IBaseDao {

	List execute(String sql, Object ...args);
	
}
