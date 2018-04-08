package com.cidac.onlineparking.module.login;

import java.io.IOException;
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
		System.out.println("in controller");
		// get the form data
		userName = req.getParameter("email");
		password = req.getParameter("password");
		System.out.println(userName+"    "+password);
		try {
			if (userName != null && password != null) {
				// call the service
				service = new LoginServiceImpl();
				result = service.loginService(userName, password);
				if (result == null) {
					rd = req.getRequestDispatcher("login/login.jsp");
					rd.forward(req, resp);
					msg = "Login Failure try once again";
					// store the message in request scope
					req.setAttribute("msg", msg);
				} else {
					System.out.println("login success");
					/*rd = req.getRequestDispatcher("login/success.jsp");
					rd.forward(req, resp);*/
					resp.sendRedirect("login/success.jsp");

				}
			}
			else {
				rd=req.getRequestDispatcher("login/login.jsp");
				rd.forward(req,resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
