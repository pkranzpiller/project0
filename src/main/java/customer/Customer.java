package customer;

import shared.Account;

public class Customer extends Account {
	private double balance;
	
	public Customer(){}

	public Customer(int id, String username, String password, String fname, String lname, double balance) { //used for creating new customer
		super();
		this.username = username;
		this.firstName = fname;
		this.lastName = lname;
		this.password = password;
		this.balance = 0;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}
	
//	public Customer login(String username, String password) {
//		
//	}
	
//	private boolean authenticate() {
//		
//	}
	
	
	
	
}
