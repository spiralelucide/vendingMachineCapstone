package com.techelevator;

public class GumItem extends VendingMachineItem {

	public GumItem(String name, DollarAmount price) {
		super(name, price);
	}

	@Override
	public String getSound() {
		return "Chew Chew, Yum!";
	}

}
