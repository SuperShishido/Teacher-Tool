package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.DbConnection;

public class LoginModel {
	
	Connection connection;
	
	public LoginModel() {
		try {
			this.connection = DbConnection.getConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		if (this.connection == null) {
			System.exit(1);
		}
	}
	
	public boolean isDatabaseConnected() {
		return this.connection != null;
	}
	
	public boolean isLogin(String id) throws Exception {
		
		PreparedStatement pr = null;
		ResultSet rs = null;
		
		String sql = "SELECT password FROM user_info where id = ?";
		
		try {
			pr = this.connection.prepareStatement(sql);
			pr.setString(1, id);
			
			rs = pr.executeQuery();
			
//			boolean bool1;
			if (rs.next()) {
				return true;
			}
			return false;
		}
		catch (SQLException ex) {
			return false;
		}
		
		finally {
			pr.close();
			rs.close();
		}
	}
	
}

