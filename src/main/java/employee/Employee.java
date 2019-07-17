package employee;

import customer.Account;
import shared.User;

public class Employee extends User {
	private Account account;
	
	public Employee() {
		this.account.setBalance(0);
		this.account.setStatus("open");
	}
	
	public Account getAccount() {
		return account;
	}
}
