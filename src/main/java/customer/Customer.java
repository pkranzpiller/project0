package customer;

import shared.User;

public class Customer extends User {
	private Account account;
	
	public Customer(){
		this.account = new Account();
		account.setBalance(0);
	}

	public Customer(int id, String username, String password, String fname, String lname, double balance) { //used for creating new customer
		super();
		this.username = username;
		this.firstName = fname;
		this.lastName = lname;
		this.password = password;
		this.account = new Account();
		account.setBalance(0);
	}
	
	public Account getAccount() {
		return account;
	}

	
//	public Customer login(String username, String password) {
//		
//	}
	
//	private boolean authenticate() {
//		
//	}
	
	
	
	
}
