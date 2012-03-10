package br.com.sample.models.converters;

import org.springframework.util.LinkedCaseInsensitiveMap;

import br.com.sample.dtos.Computer;
import br.com.sample.models.converters.extractors.MapExtractor;

public class ComputerConverter extends ApplicationConverter<Computer> {
	
	public Computer entityToDTO(LinkedCaseInsensitiveMap<Object> entity) throws Exception {
		Computer element = new Computer();
		MapExtractor ex = new MapExtractor(entity);

		// Fetching from entity
		String id		= ex.getString	("id");
		String name 	= ex.getString	("name");

		// Pushing into DTO
		element.setId	(id);
		element.setName	(name); 
		
		return element;
	}
	
}
