package br.com.sample.models.business;

import java.util.ArrayList;
import java.util.List;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sample.dtos.Component;
import br.com.sample.dtos.Computer;
import br.com.sample.models.daos.interfaces.IComponentDao;
import br.com.sample.models.daos.interfaces.IComputerDao;

@Repository
public class ComputerBO {

	@Autowired
	private IComputerDao computerDao;
	
	@Autowired
	private IComponentDao componentDao;
	
	public List<Computer> all() throws Exception {
		return computerDao.all();
	}
	
	public List<Computer> allWithComponents() throws Exception {
		List<Computer> computers = computerDao.all();
		
		List<Computer> aux = new ArrayList<Computer>();
		for (Computer computer : computers) {
			aux.add(get(computer.getId()));
		}

		computers = null;
		return aux;
	}
	
	public Computer get(String ukey) throws Exception {
		Computer computer = computerDao.get(ukey);
		
		if (computer != null)
			computer.setComponents( componentDao.getComputerComponents(ukey) );
		else 
			throw new NotFoundException("Element not found");
		
		
		return computer;
	}

	public List<Component> getComponents(String ukey) throws Exception {
		
		List<Component> components = componentDao.getComputerComponents(ukey);
		
		if (components == null)
			throw new NotFoundException("Element not found");
		
		return components;
	}

	public Component getComputerComponent(String computerUkey, String componentUkey) throws Exception {
		Component component = componentDao.getComputerComponent(computerUkey, componentUkey);
		
		if (component == null)
			throw new NotFoundException("Element not found");
		
		return component;
	}
}
