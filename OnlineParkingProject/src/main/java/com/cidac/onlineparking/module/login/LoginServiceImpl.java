package com.cidac.onlineparking.module.login;

import java.sql.SQLException;

import javax.naming.NamingException;

public class LoginServiceImpl {
	public String loginService(String email, String password) throws SQLException, NamingException {
		LoginDAOImpl dao=null;
		//get the LoginDaoImpl Object
		dao=new LoginDAOImpl();
		//call the dao methintod
		System.out.println("in serveice");
		return dao.checkLogin(email, password);
	}

}
