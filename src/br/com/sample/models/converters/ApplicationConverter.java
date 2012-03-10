package br.com.sample.models.converters;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.LinkedCaseInsensitiveMap;

public abstract class ApplicationConverter<T> {

	Logger logger;
	
	private final Class<T> oClass;
	
	@SuppressWarnings("unchecked")
	public ApplicationConverter() {
		 this.oClass = (Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass() ).getActualTypeArguments()[0];
		 logger = Logger.getLogger( getObjectClass().getName() );
	}
	
	public Class<T> getObjectClass() { return oClass; }
	
	public List<T> entitiesToDTOs(List<?> list) throws Exception {
		List<T> elements = new ArrayList<T>();
		
		for (Object c : list) {
			LinkedCaseInsensitiveMap<Object> aux = (LinkedCaseInsensitiveMap<Object>) c;
			
			T e = entityToDTO(aux);
			
			elements.add(e);
		}
		
		return elements;
	}
	
	public T entitiesToDTO(List<?> result) throws Exception {
		List<?> list = entitiesToDTOs(result);
		
		if (list.size() == 1) return (T) list.get(0);
		else 				  return null;
	}
	
	public abstract T entityToDTO(LinkedCaseInsensitiveMap<Object> entity) throws Exception;
}
