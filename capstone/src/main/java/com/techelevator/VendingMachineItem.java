package com.techelevator;

import java.util.List;

public abstract class VendingMachineItem {
	
	private String name;
	private DollarAmount price;
	private String stock;
	private String slot;
	private int totalSold = 0;
	
	public VendingMachineItem(String name, DollarAmount price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public DollarAmount getPrice() {
		return price;
	}
	public String getStock() {
		if(stock.equals("0")) {
			return "Sold Out";
		}
		return stock;
	}
	public String getSlot() {
		return slot;
	}
	public int getTotalSold() {
		return totalSold;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public void incrementTotalSold() {
		totalSold++;
	}
	public abstract String getSound();
}
