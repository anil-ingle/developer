package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		   PrintWriter pw=null;
		   String name=null,fname=null;
		   Cookie ck1=null,ck2=null;
		  //general settings
		   pw=res.getWriter();
		   res.setContentType("text/html");
		  //read form1/req1 data
		   name=req.getParameter("name");
     	   fname=req.getParameter("fname");
     	  //Create two in-memory cookies having form1/req1 data
     	   ck1=new Cookie("name",name);
     	   ck2=new Cookie("fname",fname);
     	  //add cookies to response
     	   res.addCookie(ck1); res.addCookie(ck2);
     	  //generate Form2 dynamically here
     	  pw.println("<form action='surl' method='post'>");
     	  pw.println("Income::<input type='text' name='income'><br>");
     	  pw.println("Tax:: <input type='text' name='tax'><br>");
     	  pw.println("<input type='submit' value='submit'>");
     	  pw.println("</form>");
	}//doGet(-,-)
     @Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	   doGet(req,res);
     }//doPost(-,-)
}//class
