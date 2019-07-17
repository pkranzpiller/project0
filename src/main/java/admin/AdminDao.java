package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionUtil;

public class AdminDao {
	
	public void executeRawStatement(String statement) {
		System.out.println("stuff");
	}

	public int authenticateAndGetId(String username, String password) {
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		int id=-1;
		try{
			PreparedStatement ps = con.prepareStatement("select id, username, password, permission from users where users.username = ?");
			ps.setString(1, username);
			results = ps.executeQuery();
			
			if(!results.next())		//if no user found
				return id;
			if(username.equals(results.getString("username")) && password.equals(results.getString("password"))) {
				id = results.getInt("id");
				return id;
			}
			
			
		}catch(SQLException e) {
			System.out.println("Error: Couldn't authenticat and get ID");
			e.printStackTrace();
		}
		return id;
	}

	public Admin getUserById(int id) {
		Admin admin = new Admin();
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		
		try {
			//----------------------populate user info----------------------------------------
			PreparedStatement ps = con.prepareStatement("select * from users where id = ?");
			ps.setInt(1, id);
			results = ps.executeQuery();
			results.next();
			admin.setUsername(results.getString("username"));
			admin.setFirstName(results.getString("firstName"));
			admin.setLastName(results.getString("lastName"));
			admin.setId(results.getInt("id"));
			admin.setPassword(results.getString("password"));
			admin.setPermission(results.getString("permission"));
			
			return admin;
			
			
		} catch (SQLException e) {
			System.out.println("Error: couldn't get admin");
			e.printStackTrace();
			return null;
		}
	}
	
	public Admin getAdminByUsername(String username){
		Connection con = ConnectionUtil.getInstance().getConnection();
		ResultSet results;
		
		Admin admin = new Admin();
		
		try{
			//----------------------populate user info----------------------------------------
			PreparedStatement ps = con.prepareStatement("select * from users where username = ?");
			ps.setString(1, username);
			results = ps.executeQuery();
			if(!results.next()) {
				System.out.println("Couldn't find user");
				return null;
			}
			
			admin.setUsername(results.getString("username"));
			admin.setFirstName(results.getString("firstName"));
			admin.setLastName(results.getString("lastName"));
			int id = results.getInt("id");
			admin.setId(id);
			admin.setPassword(results.getString("password"));
			admin.setPermission(results.getString("permission"));
			
			//-----------------------populate user account info-------------------------------
			ps = con.prepareStatement("select * from accounts where primaryUserId = ?");
			ps.setInt(1, id);
			results = ps.executeQuery();
			results.next();
			admin.getAccount().setBalance(results.getFloat("balance"));
			admin.getAccount().setPrimaryUserId(results.getInt("id"));
			admin.getAccount().setStatus(results.getString("status"));
			
			return admin;
			
			
		}catch(SQLException e) {
			System.out.println("Error: Couldn't get user by username");
			e.printStackTrace();
		}
		return null;
	}

}
