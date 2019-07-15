package util;

import java.util.Scanner;

import customer.Customer;
import customer.CustomerDao;
import shared.Login;

public class LoginUtil {
	private Login login;
	
	
	public LoginUtil(){
		
	}
	
	public Customer loginHandler(String input) {	//return null if customer doesn't exist
		
		Scanner s = new Scanner(System.in);
		boolean validUser = false;
		Customer customer = null;//will carry customer data which we'll use after this submenu
		
		while(customer == null) {
			switch(input) {
			case "l":    //--------------------logging in-----------------------
				customer = login(customer);
				if(customer != null)
					return customer;
				else
					break;
			case "n":	//--------------------creating new account--------------
				customer = CreateNewCustomer();
				if(customer != null)
					return customer;
				else
					break;
			default:
				System.out.println("Error pulling up correct menu");
			}
		}
		return null;
	}
	
	private Customer login(Customer customer) {//method to log the user in
		int id;
		String [] user = {};
		while(user.length != 2) {
			System.out.println("Please enter your username and password (<username> <password>):");
			Scanner login = new Scanner(System.in);
			user = login.nextLine().split(" ");
		}
		
		CustomerDao customerData = new CustomerDao();
		id = customerData.authenticateAndGetId(user[0], user[1]);
		if(id > 0) {	//id return -1 when it can't retrieve information successfully
			customer = customerData.getCustomer(id);
//			System.out.println(customer.getUsername() + " " + customer.getFirstName() + " " + customer.getLastName());
			return customer;
		}else {
			System.out.println("Invalid login, please try again or restart to create an account");
			return null;
		}
	}
	
	private Customer CreateNewCustomer() {
		String [] input = {};
		Customer customer = new Customer();
		Scanner s = new Scanner(System.in);
		while(input.length != 2) {
			System.out.println("Please enter a username and password, separated by a space (<username> <password>)");
			input = s.nextLine().split(" ");
		}
		
		
		
		CustomerDao customerData = new CustomerDao();
		customer.setUsername(input[0]);
		customer.setPassword(input[1]);
		
		
		if(customerData.checkIfAccountExists(customer.getUsername()) == false) {		//Check if username already exists. Return null on existing customer
			System.out.println("Account already exists. Please select a different username");
			return null;
		}
		
		String [] input2 = {};
		
		s = new Scanner(System.in);
		
		while(input2.length != 2) {
			System.out.println("Please enter your first name and last name, separated by a space(<first name> <last name>)");
			input2 = s.nextLine().split(" ");
		}
		
		customer.setFirstName(input2[0]);
		customer.setLastName(input2[1]);
		
		customerData.insert(customer);
		
		s.close();
		return customer;
		
		
		
		
		
	}
	
}
