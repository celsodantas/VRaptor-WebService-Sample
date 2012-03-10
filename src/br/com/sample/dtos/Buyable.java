package br.com.sample.dtos;

public abstract class Buyable {

	private String id;
	private String name;
	protected Double price;
	
	/*
	 * Getters and Setters
	 */
	public String getId() { return id;	}
	public void setId(String id) {	this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public Double getPrice() { return price; }
	public void setPrice(Double price) { this.price = price; }

}
