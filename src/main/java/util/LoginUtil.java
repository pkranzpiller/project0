package util;

import java.util.Scanner;

import customer.Customer;
import customer.CustomerDao;
import employee.Employee;

public class LoginUtil {
	
	
	public LoginUtil(){
		
	}
	
	
	
	public Customer customerLoginHandler(String input) {	//return null if customer doesn't exist
		
		Customer customer = null;//will carry customer data which we'll use after this submenu
		
		while(customer == null) {
			switch(input) {
			case "l":    //--------------------logging in-----------------------
				customer = customerLogin();
				if(customer != null){
					return customer;
				}
				else
					return null;
			case "n":	//--------------------creating new account--------------
				customer = createNewCustomer();
				if(customer != null) {
					System.out.println("account created and set for pending");
					return customer;
				}else
					return null;
			default:
				System.out.println("Error pulling up correct menu");
				return null;
			}
		}
		return null;
	}
	
	private Customer customerLogin() {//method to log the user in
		int id;
		Customer customer;
		String username;
		String password;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Please enter your username: ");
		username = s.next();
		s.nextLine();
		System.out.println("Please enter your password: ");
		password = s.next();
		s.nextLine();
		
		
		
		CustomerDao customerData = new CustomerDao();
		id = customerData.authenticateAndGetId(username, password);
		if(id > 0) {	//id return -1 when it can't retrieve information successfully
//			System.out.println(customer.getUsername() + " " + customer.getFirstName() + " " + customer.getLastName());
			customer = customerData.getCustomerById(id);
			return customer;
		}else {
			System.out.println("Invalid login, please try again");
			return null;
		}
	}
	
	
	
	
	public Customer createNewCustomer() {
		String input;
		Customer customer = new Customer();
		CustomerDao customerData = new CustomerDao();

		Scanner s = new Scanner(System.in);
		
		//---------------------------------------get user information--------------------------------------------------
		System.out.println("Please enter a username. Any spaces will be truncated");
		input = s.next();
		s.nextLine();
		customer.setUsername(input);
		System.out.println("Username is: " + customer.getUsername());
		
		
		if(customerData.checkIfAccountExists(customer.getUsername())){
			System.out.println("Account already exists, please try again");
			return null;
		}
		
		System.out.println("Enter a password. Any spaces will be truncated");
		input = s.next();
		s.nextLine();
		customer.setPassword(input);
		System.out.println("Password is: " + customer.getPassword() );
		
		System.out.println("Enter your first name. Any spaces will be truncated");
		input = s.next();
		s.nextLine();
		customer.setFirstName(input);
		System.out.println("First name is: " + customer.getFirstName());
		
		System.out.println("Enter your last name. Any spaces will be truncated");
		input = s.next();
		s.nextLine();
		customer.setLastName(input);
		System.out.println("Last name is: " + customer.getLastName());
		
		customer.setPermission("customer");
		
		//-------------------------------------------------insert user information to database------------------------------------------
		
		
		customerData.insert(customer);
		System.out.println("Customer info entered is: " + " " + customer.getId() + " " + customer.getUsername() + " "
		+ customer.getPassword() + " " + customer.getFirstName() + " " + 
				customer.getLastName() + " " + customer.getPermission());
		
		return customer;
	}
	
}
