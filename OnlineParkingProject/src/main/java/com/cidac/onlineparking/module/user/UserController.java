package com.cidac.onlineparking.module.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cidac.onlineparking.utilty.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
@WebServlet("/user")
public class UserController extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession=req.getSession(false);
		//if(httpSession!=null) {
       System.out.println("req come"); 
       this.getCity(resp);
       System.out.println("req come"); 

		//}else {
		//	throw new IllegalAccessError("you must login first");
		//}
	}

	private void getCity(HttpServletResponse resp) throws IOException {
		String city=JsonUtil.convertJavaToJson(new UserServiceImpl().selectCity());
		System.out.println(city);
		resp.setContentType("Application/Json");
		resp.getWriter().write(city);

	}

}
