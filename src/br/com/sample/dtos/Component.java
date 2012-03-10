package br.com.sample.dtos;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("component")
public class Component extends Buyable {
	
	// Belongs to 
	private Computer computer;
	
	private Boolean deffault;
	
	/*
	 * Getters and Setters
	 */
	public void setComputer(Computer computer) { this.computer = computer; }
	public Computer getComputer() { return this.computer; }
	public Boolean getDeffault() { return deffault; }
	public void setDeffault(Boolean deffault) { this.deffault = deffault; }
}
