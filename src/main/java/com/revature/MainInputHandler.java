package com.revature;

import customer.CustomerMainMenu;
import employee.EmployeeMainMenu;

public class MainInputHandler {
	private String input;

	public MainInputHandler(String input) {
		this.input = input;
	}

	public void startApplication() {
		switch (input) {
		case "customer":
			System.out.println("Starting customer application");
			CustomerMainMenu.start();
			return;
		case "employee":
			System.out.println("Starting employee application");
			EmployeeMainMenu.start();
			return;
		case "admin":
			System.out.println("Starting administrator application");
			return;
		default:
			System.out.println("Invalid input. Please pass customer, employee, or admin to start the correct application");
			return;
		}
	}
}
