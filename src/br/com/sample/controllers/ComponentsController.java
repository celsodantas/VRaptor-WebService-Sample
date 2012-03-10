package br.com.sample.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.sample.dtos.Component;
import br.com.sample.models.business.ComponentBO;

@Resource
public class ComponentsController extends ApplicationController {

	@Autowired
	private ComponentBO componentBO;
	
	public ComponentsController(Result result) { super(result); }
	
	@Path("components/{ukey}.json")
	public void show(String ukey) {
		try 
		{
			Component component = componentBO.get(ukey);
			
			if (component != null)
				respondWithJson(component);
			else
				respondWithNotFound();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			respondWithError();
		}
	}
	
	@Get
	@Path(value="components/search_desc.json", priority=1)
	public void search_by_desc(String value, Integer page, Integer per) {
		try 
		{
			List<Component> components = componentBO.searchByName(value, page, per);
			respondWithJson(components);
		} 
		catch (Exception e) 
		{
			System.out.println(e); 
			respondWithError();
		}
	}
}
