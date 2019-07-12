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
		
//		System.out.println(input);
//		switch(input) {
//		case l:
//			break;
//		case n:
//			break;
//		default:
//			System.out.println("Error pulling up correct menu");
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
