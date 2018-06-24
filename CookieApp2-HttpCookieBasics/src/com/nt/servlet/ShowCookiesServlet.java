package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCookiesServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		     PrintWriter pw=null;
		     Cookie cks[]=null;
		    //general settings
		     pw=res.getWriter();
		     res.setContentType("text/html");
		     //read cookies
		     cks=req.getCookies();
		     //display cookies
		     pw.println("<table border='1'>");
		     if(cks!=null){
		    	for(Cookie ck:cks){
		    		pw.println("<tr>");
		    		pw.println("<td>"+ck.getName()+"</td>");
		    		pw.println("<td>"+ck.getValue()+"</td>");
		    		pw.println("</tr>");
		    	}//for
		     }//if
		     pw.println("</table>");
		     //close stream
		     pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
}//class
