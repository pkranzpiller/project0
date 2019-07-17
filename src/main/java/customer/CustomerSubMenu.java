package customer;

import java.util.Scanner;

public class CustomerSubMenu {
	
	public CustomerSubMenu() {};
	
	public static void startSubMenu(Customer customer) {
		String input = "";
		Scanner s = new Scanner(System.in);

		if(customer.getAccount().getStatus().equalsIgnoreCase("pending")) {
			System.out.println("Account is still pending. Please try again later");
			return;
		}
		
		
		while(!input.equals("5")) {
			System.out.println("How can we help you today " + customer.getUsername() + "?");
			System.out.println("1: See Balance");
			System.out.println("2: Withdraw");	//2, 3, 4 require update of the user
			System.out.println("3: Deposit");
			System.out.println("4: Transfer");
			System.out.println("5: Exit");

			
			input = s.next();
			
			switch(input) {
			case "1":
				System.out.println("Balance is: " + customer.getAccount().getBalance());
				break;
			case "2":
				withdrawPrompt(customer);
				break;
			case "3":
				depositPrompt(customer);
				break;
			case "4":
				transferPrompt(customer);
				break;
			case "5":
				break;
			default:
				System.out.println("Invalid selection");
				break;
			}	
		}
	}
	
	private static void transferPrompt(Customer customer) {		//handles transfer entry and does some error checking
		CustomerDao customerData = new CustomerDao();
		String username;
		System.out.println("Enter the username of the person you want to transfer funds to");
		Scanner s = new Scanner(System.in);
		username = s.next();
		if(username.equals(customer.getUsername())) {							//don't let people transfer to the same account...
			System.out.println("You can't transfer to yourself ya dinugs");
			return;
		}
		if(!customerData.checkIfAccountExists(username)) {	//return if user doesn't exist
			System.out.println("The account you want to transfer to does not exist");
			return;
		}
		System.out.println("How much do you want to transfer?");
		float amount = 0;
		try {
			amount = Float.parseFloat(s.next());
		} catch (Exception e) {
			System.out.println("Invalid entry for transfer amount");	//check for valid float input
			return;
		}
		if(amount <= 0) {
			System.out.println("Amount has to be greater than zero");	//check that it's greater than zero
			return;
		}
		if(amount > customer.getAccount().getBalance()) {
			System.out.println("You don't have the amount of funds to transfer");  //make sure they have the amount to transfer
			return;
		}
		System.out.println("Right before transfer funds");
		customerData.transferFunds(customer, customerData.getCustomerByUsername(username), amount);
		
	}
	
	
	private static void depositPrompt(Customer customer) {			//handles some error checking before depositing
		System.out.println("How much will you be depositing today?");
		Scanner s = new Scanner(System.in);
		CustomerDao customerData = new CustomerDao();
		float amount = 0;
		try {
			amount = Float.parseFloat(s.next());
		} catch (Exception e) {
			System.out.println("Invalid entry for withdrawal amount");
			return;
		}
		if(amount <= 0) {
			System.out.println("Amount has to be greater than zero");
			return;
		}
		customerData.deposit(customer, amount);
	}
	
	private static void withdrawPrompt(Customer customer) {			//handles some error checking before withdrawing
		//get withdrawal amount
		CustomerDao customerData = new CustomerDao();
		System.out.println("How much would you like to withdraw?");
		Scanner num = new Scanner(System.in);
		float amount = 0;
		try {
			amount = Float.parseFloat(num.next());
		} catch (Exception e) {
			System.out.println("Invalid entry for withdrawal amount");
			return;
		}
		//check if they have that much
		if(amount > customer.getAccount().getBalance()) {
			System.out.println("You don't have the amount of funds to withdraw");
			return;
		}if(amount <= 0){
			System.out.println("Amount has to be greater than zero");
			return;
		}else {//subtract the amount if they do
			customerData.withdraw(customer, amount);
		}
	}
	

}
