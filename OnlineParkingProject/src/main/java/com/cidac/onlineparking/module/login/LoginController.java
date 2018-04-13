package com.cidac.onlineparking.module.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	@author Ram
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginServiceImpl service = null;
		String msg = null;
		String result = null;
		String userName = null;
		String password = null;
		RequestDispatcher rd = null;
		PrintWriter pw = resp.getWriter();
		// get the form data
		userName = req.getParameter("email");
		password = req.getParameter("password");
		try {
			if (userName != null && password != null) {
				// call the service
				service = new LoginServiceImpl();
				result = service.loginService(userName, password);
				if (result == null) {
					msg = "Login Failure try once again";
					// store the message in request scope
					req.setAttribute("msg", msg);

					rd = req.getRequestDispatcher("login/login.jsp");
					rd.forward(req, resp);
				} else {
					req.setAttribute("userName", result);
					rd = req.getRequestDispatcher("login/success.jsp");
					rd.forward(req, resp);
				}
			} else {
				msg = "Login Failure try once again";
				req.setAttribute("msg", msg);
				rd = req.getRequestDispatcher("login/login.jsp");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			pw.println("Internal problem ! try once again");
		}
	}

}
