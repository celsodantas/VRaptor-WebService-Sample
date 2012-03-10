package br.com.sample.models.daos.interfaces;

import java.util.List;

import br.com.sample.dtos.Component;

public interface IComponentDao {

	List<Component> searchByName(String name) throws Exception;

	List<Component> searchByName(String name, Integer page) throws Exception;

	List<Component> searchByName(String name, Integer page, Integer per)
			throws Exception;

	Component get(String ukey) throws Exception;

	List<Component> getComputerComponents(String compUkey) throws Exception;

	Component getComputerComponent(String compUkey, String componentUkey)
			throws Exception;

}
