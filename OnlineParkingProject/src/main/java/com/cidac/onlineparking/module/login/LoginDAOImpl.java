package com.cidac.onlineparking.module.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import com.cidac.onlineparking.utilty.DBConnection;
import com.cidac.onlineparking.utilty.UserQuery;
/*
 @author Ram
 */
public class LoginDAOImpl {
	public String checkLogin(String email, String password) throws SQLException, NamingException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		String name=null;
		// get the connection object
		con = DBConnection.getConnection();
		if (con != null) {
			//call PreparedStatement Object
			ps = con.prepareStatement(UserQuery.SELECT_LOGIN_QUERY);
			if (ps != null) {
				// set the param values
				ps.setString(1, email);
				ps.setString(2, password);
				// execute the query
				rs = ps.executeQuery();
				if (rs != null) {
					while(rs.next()) {
						System.out.println(rs.getString(3));
						name=rs.getString(3);
						int count = rs.getRow();
						System.out.println(count);
						if(count != 0) {
							return name;
						}
					}
				}
			}

		}
		return name;
	}

}
