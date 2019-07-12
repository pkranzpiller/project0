package customer;

import shared.Account;

public class Customer extends Account {
	private double balance;
	
	Customer(){}

	Customer(String username, String password, String fname, String lname) { //used for creating new customer
		super();
		this.username = username;
		this.firstName = fname;
		this.lastName = lname;
		this.password = password;
		this.balance = 0;
	}


	public double getBalance() {
		return balance;
	}
	
	public Customer login(String username, String password) {
		
	}
	
	private boolean authenticate() {
		
	}
	
	
	
	
}
