package br.com.caelum.vraptor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.deserialization.Deserializer;
import br.com.caelum.vraptor.deserialization.Deserializes;
import br.com.caelum.vraptor.http.ParametersProvider;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.validator.Message;

/**
 * 
 * VRaptor doesn't implement correctly x-www-form-urlencoded (yet), so
 * I had to implement it. 
 * 
 * @author celsodantas
 *
 */

@Deserializes("application/x-www-form-urlencoded")
public class FormDeserializer implements Deserializer {

	private ParametersProvider provider;
	private Localization localization;
	
	public FormDeserializer(ParametersProvider provider, Localization localization) {
		this.provider = provider;
		this.localization = localization;
	}

	public Object[] deserialize(InputStream inputStream, ResourceMethod method) {
		List<Message> errors = new ArrayList<Message>();
		return provider.getParametersFor(method, errors, localization.getBundle());

	}
}