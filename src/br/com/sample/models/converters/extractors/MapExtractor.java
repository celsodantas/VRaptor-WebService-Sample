package br.com.sample.models.converters.extractors;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.util.LinkedCaseInsensitiveMap;

public class MapExtractor {
	
	LinkedCaseInsensitiveMap<Object> map = null;
	
	public MapExtractor(LinkedCaseInsensitiveMap<Object> map) {
		this.map = map;
	}
	
	public String getString(String column) {
		return (String) map.get(column);
	}
	
	public Double getDouble(String column) throws Exception {
		Object value = map.get(column);
		
		if (value == null) 					return null;
		
		if (value instanceof BigDecimal) {
			return ((BigDecimal) value).doubleValue();
		} else {								
			throw new Exception("Valor de " + column + " vindo do BD não é NULL nem BigDecimal.");
		}
	}

	public Date getDate(String column) {
		java.sql.Timestamp time = (Timestamp) map.get(column);
		return new Date(time.getTime());
	}

	public Integer getInteger(String column) {
		return (Integer) map.get(column);
	}
	
	public BigDecimal getBigDecimal(String column) {
		return (BigDecimal) map.get(column);
	}
	
	public Long getLong(String column) {
		Object obj = map.get(column);
		Long value = null;
		
		if (obj instanceof Integer) 	
			value = ((Integer) obj).longValue();
		else
			value = (Long) obj;
		
		return value;
	}

	/**
	 * Obtém o valor booleano da coluna passada por parametro.
	 * 
	 * @param column
	 * O nome da coluna que deseja obter o valor
	 * @return
	 * Caso o valor presente na coluna seja numeric. <br/> 
	 * 0 => false | 1 => true <br/>
	 * Caso o valor presente na coluna seja <i>null<i/> será retornado <i>false</i>. <br/>
	 * @throws Exception
	 */
	public Boolean getBoolean(String column) throws Exception {
		Object value = map.get(column);
		
		if (value == null) 					return false;
		
		if (value instanceof BigDecimal) {
			return (((BigDecimal) value).intValue() == 1);
		} else {								
			throw new Exception("Valor de " + column + " vindo do BD não é NULL nem BigDecimal.");
		}
	}


}
