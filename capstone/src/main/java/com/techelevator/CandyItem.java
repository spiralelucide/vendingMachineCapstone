package com.techelevator;

public class CandyItem extends VendingMachineItem {

	public CandyItem(String name, DollarAmount price) {
		super(name, price);
	}

	@Override
	public String getSound() {
		return "Munch Munch, Yum!";
	}

}
