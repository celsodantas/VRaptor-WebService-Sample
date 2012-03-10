package br.com.sample.models.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sample.dtos.Component;
import br.com.sample.models.daos.interfaces.IComponentDao;

@org.springframework.stereotype.Component
public class ComponentBO {
	
	@Autowired
	private IComponentDao componentDao;
	
	public Component get(String ukey) throws Exception {
		return componentDao.get(ukey);
	}
	
	public List<Component> searchByName(String name) throws Exception {
		return componentDao.searchByName(name);
	}
	
	public List<Component> searchByName(String name, Integer page) throws Exception {
		return componentDao.searchByName(name, page);
	}
	
	public List<Component> searchByName(String name, Integer page, Integer per) throws Exception {
		return componentDao.searchByName(name, page, per);
	}
}
