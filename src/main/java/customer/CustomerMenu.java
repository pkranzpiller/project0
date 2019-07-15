package customer;

import java.util.Scanner;

import util.LoginUtil;

public class CustomerMenu {
	
	
	public static void start() {
		Scanner s = new Scanner(System.in);
		String input = "";
		
		Customer customer = null;
		
		System.out.println("Customer Menu");
		
		while(customer==null) {
			while(!checkUser(input)) {
				System.out.println("Log in or create new account?(l, n) ");
				input = s.nextLine();
			}
			
			customer = new LoginUtil().loginHandler(input);
		}
		
		
		
		
		
		
		
		

		//--------------------------logging in and creating new customer--------------------------------
//		int id;
//		boolean validUser = false;
//		Customer customer;//will carry customer data which we'll use after this submenu
//		
//		while(!validUser) {
//			switch(input) {
//			case "l":    //--------------------logging in-----------------------
//				String [] user = {};
//				while(user.length != 2) {
//					System.out.println("Please enter your username and password (<username> <password>):");
//					Scanner login = new Scanner(System.in);
//					user = login.nextLine().split(" ");
//				}
//				
//				CustomerDao customerData = new CustomerDao();
//				id = customerData.authenticateAndGetId(user[0], user[1]);
//				if(id > 0) {
//					validUser = true;
//					customer = customerData.getCustomer(id);
//					System.out.println(customer.getUsername() + " " + customer.getFirstName() + " " + customer.getLastName());
//				}else
//					System.out.println("Invalid login, please try again or restart to create an account");
//				
//				break;
//			case "n":
//				break;
//			default:
//				System.out.println("Error pulling up correct menu");
//			}
//		}
		s.close();
	}
	
	private static boolean checkUser(String s) {
		if(s.equalsIgnoreCase("l") || s.equalsIgnoreCase("n"))
			return true;
		else
			return false;
	}

}
