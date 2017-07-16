package com.techelevator;

public class ChipItem extends VendingMachineItem{

	public ChipItem(String name, DollarAmount price) {
		super(name, price);
	}

	@Override
	public String getSound() {
		return "Crunch Crunch, Yum!";
	}

}
