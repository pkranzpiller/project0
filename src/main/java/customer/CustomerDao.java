package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import shared.Dao;
import util.ConnectionUtil;

public class CustomerDao implements Dao {

	@Override
	public void insert(Object e) {
		// TODO Auto-generated method stub
		
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
