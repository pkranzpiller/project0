package com.revature;

/**
 * Hello world!
 *
 */
public class BankingApp {
    public static void main(String[] args) {
    	if(args.length != 1) {
    		System.out.println("Error: Invalid number of parameters passed. Please pass one of the following parameters: customer, employee, admin.");
    		System.out.println("Example: BankingApp <parameter>");
    	}
    	MainInputHandler m = new MainInputHandler(args[0]);
    	m.startMenu();
    }

}
