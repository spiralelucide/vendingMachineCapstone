package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_SALES_REPORT,
														MAIN_MENU_OPTION_DISPLAY_ITEMS,
													   MAIN_MENU_OPTION_PURCHASE };
	private static final String[] PURCHASING_MENU_OPTIONS = {"Sales Report", "Feed Money","Select Product","Finish Transaction"};
	
	private VendingMachine vendingMachine = new VendingMachine();
	private Menu menu; 
	private Reports report = new Reports();
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	
	public void run() {
		vendingMachine.stock();
		
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				menu.displayProductInfo(vendingMachine.getProducts());
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchase();
			} else if(choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
				report.salesReport(vendingMachine);
			}
		}
	}
	public void purchase() {
		String choice = "";
		while(!choice.equals("Finish Transaction")) {
			choice = (String) menu.getChoiceFromOptions(PURCHASING_MENU_OPTIONS);
			if(choice.equals("Feed Money")){
				System.out.println("\nHow much money do you insert?");
				vendingMachine.feedMoney(menu.userMoneyFeed());			
				System.out.println("Current Balance : " + vendingMachine.getCurrentMoney());
			} else if(choice.equals("Select Product")) {
				System.out.println("\nPlease choose your product.");
				System.out.println(vendingMachine.selectProduct(menu.userSelectsProduct()));
				System.out.println("Current Balance : " + vendingMachine.getCurrentMoney().toString());
			} else {
				System.out.println(vendingMachine.returnMoney());
				for (VendingMachineItem item : vendingMachine.getItemsPurchased()) {
					String sound = item.getSound();
					System.out.println(sound);
				} 
			} 
		}
		
		
	}
	
}
