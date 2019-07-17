package admin;

import java.util.Scanner;

import customer.Customer;
import customer.CustomerDao;
import util.LoginUtil;

public class AdminSubMenu {
	
	public static void start(Admin admin) {
		while(true) {
			System.out.println("What would you like to do?");
			System.out.println("1: View customer account information by username");
			System.out.println("2: Approve, open or close customer accounts");
			System.out.println("3: Edit account");
			System.out.println("4: Create new customer account");
			System.out.println("5: Exit");
			
			Scanner s = new Scanner(System.in);
			try {
				String input = s.next();
				switch(input) {
				case "1":
					viewAccountInfo();
					break;
				case "2":
					changeAccountStatus();
					break;
				case "3":
					editAccount();
					break;
				case "4":
					LoginUtil loginUtil = new LoginUtil();
					loginUtil.createNewCustomer();
					System.out.println("");
					break;
				case "5":
					return;
				default:
					System.out.println("Invalid entry, try again");
				}
			
			} catch (Exception e2) {
				System.out.println("Invalid entry");
			}
		}
		
	}
	
	private static void editAccount() {
//		System.out.println("Please enter your SQL statement");
//		Scanner s = new Scanner(System.in);
//		String input = s.nextLine();
//		
//		AdminDao adminDao = new AdminDao();
//		adminDao.executeRawStatement(input);
		System.out.println("still needs implementing");
		
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
		if(!customer.getPermission().equals("customer")) {
			System.out.println("User is not a customer. Edit account to make necessary changes");
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
	
	private static void viewAccountInfo() {
		System.out.println("What is the username you're trying to look up");
		Scanner s = new Scanner(System.in);
		String username = null;
		try {
			username = s.next();
		} catch (Exception e) {
			System.out.println("Invalid entry for username");
			return;
		}
		
		//get customer info
		AdminDao adminData = new AdminDao();
		Admin user = adminData.getAdminByUsername(username);
		if(user == null) {
			System.out.println("Customer doesn't exist");
			return;
		}
		
		System.out.println("Customer info");
		System.out.println("User ID: " + user.getId());
		System.out.println("Username: " + user.getUsername());
		System.out.println("First Name: " + user.getFirstName());
		System.out.println("Last Name: " + user.getLastName());
		System.out.println("Permission Level: " + user.getPermission());
		System.out.println("Balance: " + user.getAccount().getBalance());
		System.out.println();
	}
	
	

}
