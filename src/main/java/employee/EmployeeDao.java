package employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import customer.Customer;
import util.ConnectionUtil;

public class EmployeeDao {
	
	public int authenticateAndGetId(String username, String password) {//return positive integer for successful id
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		int id=-1;
		try{
			PreparedStatement ps = con.prepareStatement("select id, username, password, permission from users where users.username = ?");
			ps.setString(1, username);
			results = ps.executeQuery();
			
			if(!results.next())		//if no user found
				return id;
			if(username.equals(results.getString("username")) && password.equals(results.getString("password")) && results.getString("permission").equals("employee")) {
				id = results.getInt("id");
				return id;
			}
			
			
		}catch(SQLException e) {
			System.out.println("Error: SQL Exception");
			e.printStackTrace();
		}
		return id;
	}
	
	public Employee getEmployeeById(int id) {
		Employee employee = new Employee();
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		
		try {
			//----------------------populate user info----------------------------------------
			PreparedStatement ps = con.prepareStatement("select * from users where id = ?");
			ps.setInt(1, id);
			results = ps.executeQuery();
			results.next();
			employee.setUsername(results.getString("username"));
			employee.setFirstName(results.getString("firstName"));
			employee.setLastName(results.getString("lastName"));
			employee.setId(results.getInt("id"));
			employee.setPassword(results.getString("password"));
			employee.setPermission(results.getString("permission"));
			
			return employee;
			
			
		} catch (SQLException e) {
			System.out.println("Error: couldn't get employee");
			e.printStackTrace();
			return null;
		}
	}
}
