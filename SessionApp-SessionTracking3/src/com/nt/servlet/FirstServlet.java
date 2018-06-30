package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FirstServlet extends HttpServlet {
		
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,addrs=null;
		int age=0;
		HttpSession ses=null;
		//general settings
		res.setContentType("text/html");
		pw=res.getWriter();
	   //read form1/req1 data
		name=req.getParameter("name");
		addrs=req.getParameter("addrs");
		age=Integer.parseInt(req.getParameter("age"));
		//create HttpSession obj for Browser on Server
		ses=req.getSession(true);
		//write form1/req1 data to Session attributes
		ses.setAttribute("name",name);
		ses.setAttribute("addrs", addrs);
		ses.setAttribute("age",age);
		//generate Form2 Dynamically
		pw.println("<h1 style='color:red;text-align:center'>Naukri.com-Registation page2</h1>");
		pw.println("<form action='surl' method='get'>");
		pw.println("Skill Set::<select name='skill'>");
		pw.println("<option value='java'>Java/J2ee</option>");
		pw.println("<option value='.net'>.net</option>");
		pw.println("<option value='oracle'>Oracle</option>");
		pw.println("<option value='php'>PHP</option>");
		pw.println("</select><br>");
		pw.println("Experience::<input type='text' name='exp'><br>");
		pw.println("<input type='submit' value='continue'>");
		pw.println("</form>");
		pw.println("<br><b>Session Id::"+ses.getId()+"</b>");
		
		pw.println("<h3 style='color:red'>Req count::"+req.getServletContext().getAttribute("reqCount")+"</h3>");
		//close stream
		pw.close();		
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
	

}//class
