package com.revature;

import customer.CustomerMenu;

public class MainInputHandler {
	private String input;

	public MainInputHandler(String input) {
		super();
		this.input = input;
	}

	private boolean checkInput() {
		switch (input) {
		case "customer":
//			System.out.println("call customer");
			return true;
		case "employee":
			System.out.println("Call employee");
			return true;
		case "admin":
			System.out.println("Call admin");
			return true;
		default:
			System.out.println("Invalid input");
			return false;
		}
	}
	
	public void startMenu() {
		if(this.checkInput()) {
//			System.out.println("Do stuff");
			CustomerMenu.start();
		}else {
			System.out.println("Error: Incorrect parameters. Please pass one of the following parameters: customer, employee, admin.");
			System.out.println("Example: BankingApp <parameter>");
		}
	}
}
