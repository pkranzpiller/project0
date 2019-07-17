package customer;

import java.util.Scanner;

import util.LoginUtil;

public class CustomerMainMenu {
	
	
	public static void start() {
		
		String input = "";
		
		Customer customer = null;
		
		System.out.println("Customer Menu");

		
		boolean shutdown = false;
		
		while(!shutdown) {
			Scanner s = new Scanner(System.in);
			System.out.println("Log in, create new account, or shut down?(l, n, s) ");
			input = s.next();
			
			if(input.equalsIgnoreCase("l")) {
				customer = new LoginUtil().customerLoginHandler(input);
				if(customer != null)
					CustomerSubMenu.startSubMenu(customer);		//only start customer submenu if customer is not null
			}else if (input.equalsIgnoreCase("n")) {
				customer = new LoginUtil().customerLoginHandler(input);
			}else if(input.equalsIgnoreCase("s")){
				shutdown = true;
				System.out.println("shutting down");
			}
		}
		
	}
	
	
	

}
