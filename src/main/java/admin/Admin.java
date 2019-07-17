package admin;

import customer.Account;
import shared.User;

public class Admin extends User {
	private Account account;
	
	public Admin() {
		this.account = new Account();
		account.setBalance(0);
		account.setStatus("null");
	}
	
	public Account getAccount() {
		return account;
	}
	
}
