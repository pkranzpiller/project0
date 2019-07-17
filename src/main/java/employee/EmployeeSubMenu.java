package employee;

import java.util.Scanner;

import customer.Customer;
import customer.CustomerDao;

public class EmployeeSubMenu {
	
	public static void start(Employee e) {
		
		while(true) {
			System.out.println("What would you like to do?");
			System.out.println("1: View customer account information by username");
			System.out.println("2: Approve, open or close customer accounts");
			//TODO deposit or withdraw money for the customer
			System.out.println("3: Exit");
			
			Scanner s = new Scanner(System.in);
			try {
				String input = s.next();
				switch(input) {
				case "1":
					viewCustomerInfo();
					break;
				case "2":
					changeAccountStatus();
					break;
				case "3":
					return;
				default:
					System.out.println("Invalid entry, try again");
				}
			
			} catch (Exception e2) {
				System.out.println("Invalid entry");
			}
		}
	}
	
	private static void changeAccountStatus() {
		System.out.println("What is the customers username");
		Scanner s = new Scanner(System.in);
		String username = null;
		try {
			username = s.next();
		} catch (Exception e) {
			System.out.println("Invalid entry for username");
			return;
		}
		
		//get customer info
		CustomerDao customerData = new CustomerDao();
		Customer customer = customerData.getCustomerByUsername(username);
		if(customer == null) {
			System.out.println("Customer doesn't exist");
			return;
		}
		
		//what to do to the account?
		System.out.println("What do you want to do to the account with username: " + customer.getUsername() + "?");
		System.out.println("1: Approve");
		System.out.println("2: Open");
		System.out.println("3: Close");
		
		String input = null;
		try {
			input = s.next();
		} catch (Exception e) {
			System.out.println("Invalid selection");
			return;
		}
		
		switch(input) {
		case "1":
			customerData.setAccountStatus(customer, "open");
			break;
		case "2":
			customerData.setAccountStatus(customer, "open");
			break;
		case "3":
			customerData.setAccountStatus(customer, "closed");
			break;
		default:
			System.out.println("Invalid selection");
			return;
		}
		
		
		
	}
	
	private static void viewCustomerInfo() {
		System.out.println("What is the customers username");
		Scanner s = new Scanner(System.in);
		String username = null;
		try {
			username = s.next();
		} catch (Exception e) {
			System.out.println("Invalid entry for username");
			return;
		}
		
		//get customer info
		CustomerDao customerData = new CustomerDao();
		Customer customer = customerData.getCustomerByUsername(username);
		if(customer == null) {
			System.out.println("Customer doesn't exist");
			return;
		}
		
		System.out.println("Customer info");
		System.out.println("User ID: " + customer.getId());
		System.out.println("Username: " + customer.getUsername());
		System.out.println("First Name: " + customer.getFirstName());
		System.out.println("Last Name: " + customer.getLastName());
		System.out.println("Balance: " + customer.getAccount().getBalance());
		System.out.println("Account Status: " + customer.getAccount().getStatus());
		System.out.println();
	}

}
