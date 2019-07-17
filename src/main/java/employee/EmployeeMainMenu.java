package employee;
import java.util.Scanner;

public class EmployeeMainMenu {
	
	public EmployeeMainMenu() {}
	
	
	public static void start() {
		System.out.println("Employee Menu");
		boolean shutdown = false;
		Employee emp = null;
		Scanner s = new Scanner(System.in);
		String input;
		while(!shutdown) {
			System.out.println("login or shut down(l, s)");
			input = s.next();
			switch(input) {
			case "l":
				emp = employeeLogin();
				if(emp != null)
					EmployeeSubMenu.start(emp);
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
	
	
	
	
	
	
	
	private static Employee employeeLogin() {
		String username = "";
		String password = "";
		
		EmployeeDao empDao = new EmployeeDao();
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
		
		int id = empDao.authenticateAndGetId(username, password);
		if(id > 0) {
			return empDao.getEmployeeById(id);
		}
		System.out.println("Invalid login");
		return null;
		
	}

}
