package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shared.Dao;
import util.ConnectionUtil;

public class CustomerDao implements Dao<Customer> {
	
	public void setAccountStatus(Customer customer, String status) {
		Connection con = ConnectionUtil.getInstance().getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("update accounts set status = ? where accounts.primaryUserId = ?");
			ps.setString(1, status);
			ps.setInt(2, customer.getId());
			ps.executeUpdate();
			customer.getAccount().setStatus(status);
		} catch (SQLException e) {
			System.out.println("Unable to update account permissions");
			e.printStackTrace();
		}
		
	}
	
	public void transferFunds(Customer source,Customer destination, Float amount) {
		float newSourceBalance = source.getAccount().getBalance() - amount;
		float newDestinationBalance = destination.getAccount().getBalance() + amount;
		
		Connection con = ConnectionUtil.getInstance().getConnection();
		try {
			//-----------------update source balance----------------------------
			PreparedStatement ps = con.prepareStatement("update accounts set balance = ? where accounts.primaryUserId = ?");
			ps.setFloat(1, newSourceBalance);
			ps.setInt(2, source.getId());
			ps.executeUpdate();
			
			
			//-----------------update destination balance----------------------
			ps = con.prepareStatement("update accounts set balance = ? where accounts.primaryUserId = ?");
			ps.setFloat(1, newDestinationBalance);
			ps.setInt(2, destination.getId());
			ps.executeUpdate();
			
			//----------------update source and destination account balances------
			source.getAccount().setBalance(newSourceBalance);
			destination.getAccount().setBalance(newDestinationBalance);
		} catch (SQLException e) {
			System.out.println("Couldn't get destination balance to transfer funds");
			e.printStackTrace();
		}
	}
	
	public void deposit(Customer customer, Float amount) {
		float newBalance = customer.getAccount().getBalance() + amount;
		Connection con = ConnectionUtil.getInstance().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update accounts set balance = ? where accounts.primaryUserId = ?");
			ps.setFloat(1, newBalance);
			ps.setInt(2, customer.getId());
			ps.executeUpdate();
			customer.getAccount().setBalance(newBalance);
		} catch (SQLException e) {
			System.out.println("Couldn't deposit amount in database. It's our money now! >:)");
			e.printStackTrace();
		}
	}
	
	
	public void withdraw(Customer customer, Float amount) {
		float newBalance = customer.getAccount().getBalance() - amount;
		Connection con = ConnectionUtil.getInstance().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update accounts set balance = ? where accounts.primaryUserId = ?");
			ps.setFloat(1, newBalance);
			ps.setInt(2, customer.getId());
			ps.executeUpdate();
			customer.getAccount().setBalance(newBalance);
		} catch (SQLException e) {
			System.out.println("Couldn't update withdrawal amount in database");
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void insert(Customer customer) {			//use checkIfAccountExists before calling this method and make sure to have
		try {										//at least the username, password, fname, and lname for your parameter before the call
			//----------------------------------insert basic account information into account------------------------------------
			Connection con = ConnectionUtil.getInstance().getConnection();
			ResultSet results;
			
			System.out.println("Values are as follows: " + customer.getUsername() + " " + customer.getPassword() + " " + customer.getFirstName() + " " + customer.getLastName());

			
			//--------------------------------------insert user into users table-----------------------------------------------------
			PreparedStatement ps = con.prepareStatement("insert into users(userName, password, firstName, lastName, permission) values(?, ?, ?, ?, ?)");
			
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setString(3, customer.getFirstName());
			ps.setString(4, customer.getLastName());
			ps.setString(5, "customer");
			ps.executeUpdate();
			
//			----------------------------------get account ID to enable updating of customer table------------------------------
			ps = con.prepareStatement("select id from users where username = ?");
			ps.setString(1, customer.getUsername());
			results = ps.executeQuery();
			results.next();
			customer.setId(results.getInt("id"));
			
			//--------------------------------------insert user into customers table and set for pending-----------------------------
			ps = con.prepareStatement("insert into accounts(primaryUserId, status, balance) values(?, ?, ?)");
			ps.setInt(1, customer.getId());
			ps.setString(2, "pending");
			ps.setFloat(3, 0);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: Failed to create new user");
			e.printStackTrace();
			return;	
		}
	}
	
	public boolean checkIfAccountExists(String username) {		//returns true if the account exists
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		try {
			PreparedStatement ps = con.prepareStatement("Select * from users where username = ?");
			ps.setString(1, username);
			results = ps.executeQuery();
			if(results.next())
				if(results.getString("username").equals(username))
					return true;
			
			return false;
		} catch (SQLException e) {
			System.out.println("Error: SQL exception. Failed to check if account exists");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List getAll() {				//TODO gets all customers
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		ArrayList<Customer> customers = new ArrayList<>();
		Customer customer;
		try {
			PreparedStatement ps = con.prepareStatement("select * from users where users.id =  ");
			results = ps.executeQuery();
			while(results.next()) {
			//TODO query here	
			}
		} catch (SQLException e) {
			System.out.println("SQL Error: Couldn't get all");
			e.printStackTrace();
		}
		return null;
	}
	
	public Customer getCustomer(String username, String password) {
//		Connection con = ConnectionUtil.getInstance().getConnection();
//		PreparedStatement ps = con.prepareStatement("");
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	

	public int authenticateAndGetId(String username, String password) {//return positive integer for successful id
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		int id=-1;
		try{
			PreparedStatement ps = con.prepareStatement("select id, username, password from users where users.username = ?");
			ps.setString(1, username);
			results = ps.executeQuery();
			results.next();
			if(username.equals(results.getString("username")) && password.equals(results.getString("password"))) {
				id = results.getInt("id");
				return id;
			}
			
			
		}catch(SQLException e) {
			System.out.println("Error: SQL Exception");
			e.printStackTrace();
		}
		return id;
	}
	
	public Customer getCustomerByUsername(String username) {
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		
		Customer customer = new Customer();
		
		try{
			//----------------------populate user info----------------------------------------
			PreparedStatement ps = con.prepareStatement("select * from users where username = ?");
			ps.setString(1, username);
			results = ps.executeQuery();
			if(!results.next()) {
				System.out.println("Couldn't find user");
				return null;
			}
			
			if(!results.getString("permission").equals("customer")) {
				System.out.println("Account type is not of customer");
				return null;
			}
			
			customer.setUsername(results.getString("username"));
			customer.setFirstName(results.getString("firstName"));
			customer.setLastName(results.getString("lastName"));
			int id = results.getInt("id");
			customer.setId(id);
			customer.setPassword(results.getString("password"));
			customer.setPermission(results.getString("permission"));
			
			
			
			//-----------------------populate user account info-------------------------------
			ps = con.prepareStatement("select * from accounts where primaryUserId = ?");
			ps.setInt(1, id);
			results = ps.executeQuery();
			results.next();
			customer.getAccount().setBalance(results.getFloat("balance"));
			customer.getAccount().setPrimaryUserId(results.getInt("id"));
			customer.getAccount().setStatus(results.getString("status"));
			
			return customer;
			
			
		}catch(SQLException e) {
			System.out.println("Error: Couldn't get user by username");
			e.printStackTrace();
		}
		return null;
	}
	
	public Customer getCustomerById(int id) {
		Customer customer = new Customer();
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		
		try {
			//----------------------populate user info----------------------------------------
			PreparedStatement ps = con.prepareStatement("select * from users where id = ?");
			ps.setInt(1, id);
			results = ps.executeQuery();
			results.next();
			customer.setUsername(results.getString("username"));
			customer.setFirstName(results.getString("firstName"));
			customer.setLastName(results.getString("lastName"));
			customer.setId(results.getInt("id"));
			customer.setPassword(results.getString("password"));
			customer.setPermission(results.getString("permission"));
			
			//-----------------------populate user account info-------------------------------
			ps = con.prepareStatement("select * from accounts where primaryUserId = ?");
			ps.setInt(1, id);
			results = ps.executeQuery();
			results.next();
			customer.getAccount().setBalance(results.getFloat("balance"));
			customer.getAccount().setPrimaryUserId(results.getInt("id"));
			customer.getAccount().setStatus(results.getString("status"));
			
			return customer;
			
			
		} catch (SQLException e) {
			System.out.println("SQL Exception");
			e.printStackTrace();
			return null;
		}
		
	}
	
	

}
