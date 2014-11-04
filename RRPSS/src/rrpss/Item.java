package rrpss;

import java.io.Serializable;

public class Item implements Serializable {
	protected String name;
	protected String description;
	protected double price;
	protected int id;
	
	public Item(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
