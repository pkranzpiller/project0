package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import shared.Dao;
import util.ConnectionUtil;

public class CustomerDao implements Dao<Customer> {

	@Override
	public void insert(Customer customer) {			//use checkIfAccountExists before calling this method and make sure to have
		try {										//at least the username, password, fname, and lname for your parameter before the call
			//----------------------------------insert basic account information into account------------------------------------
			Connection con = ConnectionUtil.getInstance().getConnection();
			ResultSet results;
			int id;
			
			System.out.println("Values are as follows: " + customer.getUsername() + " " + customer.getPassword() + " " + customer.getFirstName() + " " + customer.getLastName());
			
			PreparedStatement ps = con.prepareStatement("insert into account(username, password, fname, lname) values(?, ?, ?, ?)");
			
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setString(3, customer.getFirstName());
			ps.setString(4, customer.getLastName());
			ps.executeUpdate();
			//----------------------------------get account ID to enable updating of customer table------------------------------
			ps = con.prepareStatement("select id from account where username = ?");
			ps.setString(1, customer.getUsername());
			results = ps.executeQuery();
			results.next();
			id = results.getInt("id");
			//-----------------------------------insert customer information into customer table-----------------------------------
			ps = con.prepareStatement("insert into customer(id, balance) values(?, ?)");
			ps.setInt(1, id);
			ps.setDouble(2, 0);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: Failed to create new user");
			return;	
		}
	}
	
	@Override
	public boolean checkIfAccountExists(String username) {
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		try {
			PreparedStatement ps = con.prepareStatement("Select * from account where username = ?");
			ps.setString(1, username);
			results = ps.executeQuery();
			if(results.next())			//if the result exists, we know that the account exists
				return false;
			else
				return true; 			//if the result doesn't exist, then we're good
		} catch (SQLException e) {
			System.out.println("Error: SQL exception. Failed to check if account exists");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List getAll() {
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
	
//	public boolean checkIfExists() {
//		
//	}

	public int authenticateAndGetId(String username, String password) {//return positive integer for successful id
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		int id=-1;
		try{
			PreparedStatement ps = con.prepareStatement("select id, username, password from account");
			results = ps.executeQuery();
			while(results.next()) {
				if(username.equals(results.getString("username"))) {
					if(password.equals(results.getString("password"))){
						id = results.getInt("id");
						return id;
					}
				}
			}
			
		}catch(SQLException e) {
			System.out.println("Error: SQL Exception");
			e.printStackTrace();
		}
		return id;
	}
	
	public Customer getCustomer(int id) {
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		Customer customer = null;
		try {
			//get customer info from account
			PreparedStatement ps = con.prepareStatement("select id, fname, lname, username from account where id = ?");
			ps.setInt(1, id);
			results = ps.executeQuery();
			results.next();
			customer = new Customer();
			customer.setId(results.getInt("id"));
			customer.setFirstName(results.getString("fname"));
			customer.setLastName(results.getString("lname"));
			customer.setUsername("username");
			
			//get customer info from customer
			ps = con.prepareStatement("select balance from customer where id = ?");
			ps.setInt(1, id);
			results = ps.executeQuery();
			results.next();
			customer.setBalance(results.getDouble("balance"));
			
			
		}catch(SQLException e){
			System.out.println("Error: SQL Exception");
		}
		return customer;
	}

}
