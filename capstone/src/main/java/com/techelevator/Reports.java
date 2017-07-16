package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reports {
	private File logFile = new File("Log.txt");
	private PrintWriter log;
	private File salesReportFile = new File("SalesReport.csv");
	private PrintWriter salesReportWriter;
	
	public Reports() {
		try {
			log = new PrintWriter(logFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			salesReportWriter = new PrintWriter(salesReportFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void log(String method, DollarAmount moneyBefore, DollarAmount currentMoney) {
		LocalDateTime timestamp = LocalDateTime.now();
		DateTimeFormatter formatted = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
		String tolog = timestamp.format(formatted) + " " + method + " " + moneyBefore + " " + currentMoney;
		log.println(tolog);
		log.flush();
	}
	
	public void salesReport(VendingMachine vendingMachine) {
		String formatted;
                formatted = String.format("Item, number_sold", vendingMachine.getTotalSales().toString());
                salesReportWriter.println(formatted);
		for(VendingMachineItem item: vendingMachine.getProducts()) {
			formatted = String.format("%s,%d", item.getName(),item.getTotalSold());
			salesReportWriter.println(formatted);
		}
		salesReportWriter.flush();
                ProcessBuilder pb = new ProcessBuilder("Rscript","/Users/bryant/workspaces/vendingMachineCapstone/capstone");
                try{
                Process p = pb.start();
                }catch(Exception e){
                    e.printStackTrace();
                }
	}
}
