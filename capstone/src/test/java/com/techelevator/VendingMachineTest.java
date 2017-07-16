package com.techelevator;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.view.Menu;



public class VendingMachineTest {
	private VendingMachine myVendor;
	private List<VendingMachineItem> myList;
	private String[] PURCHASING_MENU_OPTIONS = { "Feed Money","Select Product","Finish Transaction"};
	private DollarAmount price;
	
	@Before
	public void setup() {
		myVendor = new VendingMachine();
		myVendor.stock();
		myList = myVendor.getProducts();
		List<VendingMachineItem> myList = myVendor.getProducts();
		
		
	}
	@Test
	public void vendingMachine_stocks_properly() {
		for(int i = 0; i < myList.size(); i++) {
			Assert.assertEquals("5", myList.get(i).getStock());
		}
		Assert.assertEquals("Potato Crisps", myList.get(0).getName());
		Assert.assertEquals("Stackers", myList.get(1).getName());
		Assert.assertEquals("Triplemint", myList.get(15).getName());
		
		price = new DollarAmount(3,5);
		Assert.assertEquals(price, myList.get(0).getPrice());
		price = new DollarAmount(1,80);
		Assert.assertEquals(price, myList.get(4).getPrice());
		price = new DollarAmount(1,25);
		Assert.assertEquals(price, myList.get(8).getPrice());
		price = new DollarAmount(85);
		Assert.assertEquals(price, myList.get(12).getPrice());
		
		Assert.assertEquals("A1", myList.get(0).getSlot());
		Assert.assertEquals("B1", myList.get(4).getSlot());
		Assert.assertEquals("C1", myList.get(8).getSlot());
		Assert.assertEquals("D1", myList.get(12).getSlot());
		
		Assert.assertEquals("A4", myList.get(3).getSlot());
		Assert.assertEquals("B4", myList.get(7).getSlot());
		Assert.assertEquals("C4", myList.get(11).getSlot());
		Assert.assertEquals("D4", myList.get(15).getSlot());
		
	}
	@Test
	public void return_money() {
		DollarAmount feed = new DollarAmount(5,0);
		myVendor.feedMoney(feed);
		String result = myVendor.returnMoney();
		Assert.assertEquals("You've received 4 dollars 0 quarters 0 dimes 0 nickels and 0 pennies", result);
		
		feed = new DollarAmount(9,94);
		myVendor.feedMoney(feed);
		result = myVendor.returnMoney();
		Assert.assertEquals("You've received 9 dollars 3 quarters 1 dimes 1 nickels and 4 pennies", result);
	}
	@Test
	public void select_product() {
		
		String result = myVendor.selectProduct("R1");
		Assert.assertEquals("Invalid slot, check the slot number again", result);
		
		result = myVendor.selectProduct("A1");
		Assert.assertEquals("Feed more money.", result);
		
		price = new DollarAmount(5,0);
		myVendor.feedMoney(price);
		result = myVendor.selectProduct("A1");
		Assert.assertEquals("Potato Crisps dispensed for $3.05", result);
		
		myVendor.feedMoney(price);
		for(int i = 0; i < 5 ; i++) {
			myVendor.selectProduct("D3");
		}
		result = myVendor.selectProduct("D3");
		Assert.assertEquals("Item sold out.", result);
		
	}
	@Test
	public void reduceStock() {
		price = new DollarAmount(5,0);
		myVendor.feedMoney(price);
		myVendor.selectProduct("A1");
		String result = myList.get(0).getStock();
		Assert.assertEquals("4", result);
	}
}
