package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachine {

	private DollarAmount totalSales = new DollarAmount(0);
	private DollarAmount currentMoney = new DollarAmount(0);
	private List<VendingMachineItem> products = new ArrayList<VendingMachineItem>();
	private List<VendingMachineItem> itemsPurchased = new ArrayList<VendingMachineItem>();
	private Reports report;
	
	public VendingMachine() {
		report = new Reports();
	}
	
	public List<VendingMachineItem> getProducts() {
		return products;
	}
	public List<VendingMachineItem> getItemsPurchased() {
		return itemsPurchased;
	}
	public DollarAmount getCurrentMoney() {
		return currentMoney;
	}
	public DollarAmount getTotalSales() {
		return totalSales;
	}
	
	public void stock() {
		File file = new File("vendingmachine.csv");
		try(Scanner items = new Scanner(file)) {
			DollarAmount price;
			while(items.hasNext()) {
				String[] arguments = items.nextLine().split("\\|");
				VendingMachineItem item;
				switch (arguments[0].substring(0, 1)){
					case "A": 
						price = DollarAmount.parseDollarAmount(arguments[2]);
						item = new ChipItem(arguments[1], price);
						break;
					case "B":
						price = DollarAmount.parseDollarAmount(arguments[2]);
						item = new CandyItem(arguments[1], price);
						break;
					case "C":
						price = DollarAmount.parseDollarAmount(arguments[2]);
						item = new BeverageItem(arguments[1], price);
						break;
					case "D":
						price = DollarAmount.parseDollarAmount(arguments[2]);
						item = new GumItem(arguments[1], price);
						break;
					default: 
						item = null;
						break;
				}
				item.setSlot(arguments[0]);
				item.setStock("5");
				products.add(item);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String returnMoney() {
		DollarAmount moneyBefore = new DollarAmount(currentMoney.hashCode());
		int dollars = currentMoney.getDollars();
		int cents = currentMoney.getCents();
		int quarters = (int)(cents / 25);
		cents %= 25;
		int dimes = (int)(cents / 10);
		cents %= 10;
		int nickels = (int)(cents / 5);
		cents %= 5;
		int pennies = (int)cents;
		cents -= pennies;
		currentMoney = new DollarAmount(cents);
		String change = ("You've received " + dollars + " dollars " + quarters + " quarters " + dimes + " dimes " + nickels + " nickels and " + pennies + " pennies");
		report.log("Give change: ", moneyBefore, currentMoney);
		return change;
	}
	
	public String selectProduct(String selection) {
		
		String regex = "[A-D][1-4]";
		if (selection.matches(regex)) {
			for (VendingMachineItem item : products) {
				if (item.getSlot().equals(selection)) {
					if (!item.getStock().equals("Sold Out")) {
						if (item.getPrice().isLessThanOrEqualTo(currentMoney)) {
							reduceStock(item);
							item.incrementTotalSold();
							totalSales = totalSales.plus(item.getPrice());
							itemsPurchased.add(item);
							DollarAmount moneyBefore = new DollarAmount(currentMoney.hashCode()); 
							currentMoney = currentMoney.minus(item.getPrice());
							report.log(item.getName() + " " + item.getSlot(), moneyBefore, currentMoney);
							return item.getName() + " dispensed for " + item.getPrice();
						} else {
							return "Feed more money.";
						}
					} else {
						return "Item sold out.";
					}
				}
			}
		}
		return "Invalid slot, check the slot number again";
	}
	
	public void feedMoney(DollarAmount feedAmount) {
		DollarAmount moneyBefore = new DollarAmount(currentMoney.hashCode());
		currentMoney = currentMoney.plus(feedAmount);
		report.log("Feed Money: ", moneyBefore, currentMoney);
	}	
	
	private void reduceStock(VendingMachineItem item) {
		int stock = Integer.parseInt(item.getStock());
		stock -= 1;
		item.setStock(Integer.toString(stock));
	}
}
