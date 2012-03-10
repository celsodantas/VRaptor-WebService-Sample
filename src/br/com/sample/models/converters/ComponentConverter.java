package br.com.sample.models.converters;

import org.springframework.util.LinkedCaseInsensitiveMap;

import br.com.sample.dtos.Component;
import br.com.sample.models.converters.extractors.MapExtractor;

public class ComponentConverter extends ApplicationConverter<Component> {
	
	public Component entityToDTO(LinkedCaseInsensitiveMap<Object> entity) throws Exception {
		Component component = new Component();
		
		MapExtractor ex = new MapExtractor(entity);
		
		// Fetching from entity
		String id			=	ex.getString("id");
		String name		 	= 	ex.getString("name");
		Double price 		=	ex.getDouble("price");
		
		// Pushing into DTO
		component.setId			(id);
		component.setName			(name);
		component.setPrice			(price);

		return component;
	}
}