package br.com.sample.utils.converters;

import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.sample.dtos.Buyable;
import br.com.sample.dtos.Component;
import br.com.sample.dtos.Computer;

@Convert(Buyable.class)
@ApplicationScoped
public class BuyableConverter implements Converter<Buyable> {
	
	/**
	 * In this application sample, this class is not being used.
	 * 
	 * It's here just for consult. If you need to pass and object to to controller
	 * that is a Computer or Component, use the interface Buyable and pass like this
	 * to the URL, if you intend to send a component object:
	 * 
	 *   localhost:8080/controler?buyable=component&buyable.name=some_name&buyable.price=9.99
	 * 
	 * if you want to send a computers
	 * 
	 *   localhost:8080/controler?buyable=computer&buyable.name=some_name&buyable.price=9.99
	 * 
	 * See below how it works.
	 * 
	 */

	@Override
	public Buyable convert(String value, Class<? extends Buyable> type, ResourceBundle bundle) {
		
		if (value.equals("component")) {
			return new Component();
		} else if (value.equals("computer")) {
			return new Computer();
		}
		return null;
	}

}
