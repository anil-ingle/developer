package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCookiesServlet extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		Cookie ck1=null,ck2=null,ck3=null,ck4=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//create In-Memory Cookies
		ck1=new Cookie("AP","amaravathi");
		ck2=new Cookie("TG","hyderabad");
		res.addCookie(ck1);
		res.addCookie(ck2);
		//create Persistent Cookies
		ck3=new Cookie("MH","mumbai");
		ck4=new Cookie("UP","luknow");
		ck3.setMaxAge(120); ck4.setMaxAge(120);
		res.addCookie(ck3); res.addCookie(ck4);
		
		pw.println("<h1>Cookies created and added to the response </h1>");
		
		//close stream
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
