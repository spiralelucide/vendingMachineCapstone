package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.techelevator.BeverageItem;
import com.techelevator.CandyItem;
import com.techelevator.ChipItem;
import com.techelevator.DollarAmount;
import com.techelevator.GumItem;
import com.techelevator.VendingMachineItem;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption >= 0 && selectedOption < options.length) {
				choice = options[selectedOption];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for(int i = 1; i < options.length; i++) {
			int optionNum = i;
			out.println(optionNum+") "+options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

	public void displayProductInfo(List<VendingMachineItem> products) {
		String formatted = String.format("%-5s%-25s%-10s%s", "Slot", "Product Name", "Price", "Quantity Left");
		out.println("\n"+ formatted);
		
		for(VendingMachineItem item : products) {
			formatted = String.format("%-5s%-25s%-10s%s", item.getSlot(), item.getName(), item.getPrice().toString(), item.getStock());
			out.println(formatted);
		}
		out.flush();
	}
	public DollarAmount userMoneyFeed() {
		DollarAmount feedAmount = DollarAmount.ZERO_DOLLARS;
		try {
			feedAmount = new DollarAmount(in.nextInt(),0);
			in.nextLine();
		}catch(InputMismatchException e) {
			System.out.println("Please provide whole dollar amount");
		}
		return feedAmount;
	}
	public String userSelectsProduct() {
		return in.nextLine();	
	}
	
}
