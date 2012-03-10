package br.com.sample.dtos;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("computer")
public class Computer extends Buyable {
	
	private List<Component> components;

	public Computer() {}
	public Computer(String id) {
		super.setId(id);
	}

	public List<Component> getComponents() { return components; }
	public void setComponents(List<Component> components) { this.components = components; }
	public void addComponent(Component component) {
		if (components == null)
			components = new ArrayList<Component>();
		
		components.add(component);
	}
	
	public Double getPrice() {
		
		price = 0.0d;
		for (Component component : components) 
		{
			price += component.getPrice();
		}
		
		return price;
	}
}
