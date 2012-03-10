package br.com.sample.controllers;

import java.util.List;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.sample.dtos.Component;
import br.com.sample.dtos.Computer;
import br.com.sample.models.business.ComputerBO;

@Resource
public class ComputersController extends ApplicationController {

	@Autowired
	private ComputerBO computerBO;

	public ComputersController(Result result) { super(result); }
	
	@Path(value="computers.json", priority=1)
	public void index(boolean load_components) {
		List<Computer> computers = null;
		
		try 
		{
			if (load_components)
				computers = computerBO.allWithComponents();
			else
				computers = computerBO.all();
			
			respondWithJson(computers);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			respondWithError();
		}
	}
	
	@Path("computers/{ukey}.json")
	public void show(String ukey) {
		try {
			Computer computer = computerBO.get(ukey);
			
			respondWithJson(computer);
		} catch (NotFoundException e) {
			respondWithNotFound();
		} catch (Exception e) {
			e.printStackTrace();
			respondWithError();
		}
	}
	
	@Path("computers/{ukey}/components.json")
	public void componets(String ukey) throws NotFoundException {
		try {
			List<Component> components = computerBO.getComponents(ukey);
			
			respondWithJson(components);
		} catch (NotFoundException e) {
			respondWithNotFound();
		} catch (Exception e) {
			e.printStackTrace();
			respondWithError();
		}
	}
	
	@Path("computers/{computerUkey}/components/{componentUkey}.json")
	public void computerComponent(String computerUkey, String componentUkey) {
		try {
			Component component = computerBO.getComputerComponent(computerUkey, componentUkey);
			
			respondWithJson(component);
		} catch (NotFoundException e) {
			respondWithNotFound();
		} catch (Exception e) {
			e.printStackTrace();
			respondWithError();
		}
	}
}
