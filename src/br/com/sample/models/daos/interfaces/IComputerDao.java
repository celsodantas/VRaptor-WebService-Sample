package br.com.sample.models.daos.interfaces;

import java.util.List;

import br.com.sample.dtos.Computer;

public interface IComputerDao {

	List<Computer> all() throws Exception;

	Computer get(String ukey) throws Exception;

}
