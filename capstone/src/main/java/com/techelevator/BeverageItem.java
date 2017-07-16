package com.techelevator;

public class BeverageItem extends VendingMachineItem{

	public BeverageItem(String name, DollarAmount price) {
		super(name, price);
	}

	@Override
	public String getSound() {
		return "Glug Glug, Yum!";
	}

}
