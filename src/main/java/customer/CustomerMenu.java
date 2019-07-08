package customer;

import java.util.Scanner;

public class CustomerMenu {
	
	
	public static void start() {
		Scanner s = new Scanner(System.in);
		String input = "";
		
		System.out.println("Customer Menu");
		
		while(!checkUser(input)) {
			System.out.println("Are you a new or returning user? (n, r): ");
			input = s.nextLine();
		}
		
		System.out.println(input);
		
		
	}
	
	private static boolean checkUser(String s) {
		if(s.equals("n") || s.equals("r"))
			return true;
		else
			return false;
	}

}
