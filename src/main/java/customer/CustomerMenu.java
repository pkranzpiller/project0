package customer;

import java.util.Scanner;

public class CustomerMenu {
	
	
	public static void start() {
		Scanner s = new Scanner(System.in);
		String input = "";
		
		System.out.println("Customer Menu");
		
		while(!checkUser(input)) {
			System.out.println("Log in or create new account?(l, n) ");
			input = s.nextLine();
		}
		

		//--------------------------logging in and creating new customer--------------------------------
		int id;
		boolean validUser = false;
		Customer customer;
		
		while(!validUser) {
			switch(input) {
			case "l":
				String [] user = {};
				while(user.length != 2) {
					System.out.println("Please enter your username and password (<username> <password>):");
					Scanner login = new Scanner(System.in);
					user = login.nextLine().split(" ");
				}
				
				CustomerDao customerData = new CustomerDao();
				id = customerData.authenticateAndGetId(user[0], user[1]);
				if(id > 0) {
					validUser = true;
					customer = customerData.getCustomer(id);
					System.out.println(customer.getUsername() + " " + customer.getFirstName() + " " + customer.getLastName());
				}
				
				break;
			case "n":
				break;
			default:
				System.out.println("Error pulling up correct menu");
			}
		}
		s.close();
	}
	
	private static boolean checkUser(String s) {
		if(s.equalsIgnoreCase("l") || s.equalsIgnoreCase("n"))
			return true;
		else
			return false;
	}

}
