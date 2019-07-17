package admin;

import java.util.Scanner;

import employee.Employee;
import employee.EmployeeDao;
import employee.EmployeeSubMenu;

public class AdminMainMenu {

	public static void start() {
		System.out.println("Admin Menu");
		boolean shutdown = false;
		Admin admin = null;
		Scanner s = new Scanner(System.in);
		String input;
		while(!shutdown) {
			System.out.println("login or shut down(l, s)");
			input = s.next();
			switch(input) {
			case "l":
				admin = adminLogin();
				if(admin != null)
					AdminSubMenu.start(admin);
				break;
			case "s":
				shutdown = true;
				return;
			case "shutdown":
				shutdown = true;
				return;
			default:
				System.out.println("Invalid entry");
			}
		}
	}
	
	private static Admin adminLogin() {
		String username = "";
		String password = "";
		
		AdminDao adminDao = new AdminDao();
		Scanner s = new Scanner(System.in);
		
		try {
			System.out.println("Please enter your username: ");
			username = s.next();
			System.out.println("Please enter your password: ");
			password = s.next();
		} catch (Exception e) {
			System.out.println("Invalid login");
			return null;
		}
		
		int id = adminDao.authenticateAndGetId(username, password);
		if(id > 0) {
			return adminDao.getUserById(id);
		}
		System.out.println("Invalid login");
		return null;
		
	}
	
}
