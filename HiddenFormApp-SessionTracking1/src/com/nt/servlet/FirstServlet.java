package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 String name=null,fname=null,ms=null;
		//general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read form1/req1 data
		 name=req.getParameter("name");
		 fname=req.getParameter("fname");
		 ms=req.getParameter("ms");
		 //generate form2 dynamically based on marital status
		 if(ms.equalsIgnoreCase("married")){
			 pw.println("<h1 style='color:red;text-align:center'>Provide Marriage Life Details </h1>");
			 pw.println("<form action='surl' method='post'>");
			 pw.println("<br> Spouse:: <input type='text' name='f2t1'><br>");
			 pw.println("<br> No.of Kids:: <input type='text' name='f2t2'><br>");
			 //add form1/req1 data in form2 as hidden box value..
			 pw.println("<input type='hidden' name='hname' value='"+name+"'>");
			 pw.println("<input type='hidden' name='hfname' value='"+fname+"'>");
			 pw.println("<input type='hidden' name='hms' value='"+ms+"'>");
			 pw.println("<input type='submit' value='submit'>");
			 pw.println("</form>");
		 }
		 else{
			 pw.println("<h1 style='color:red;text-align:center'>Provide Happy Life Details </h1>");
			 pw.println("<form action='surl' method='post'>");
			 pw.println("<br>When do u want to marry:: <input type='text' name='f2t1'><br>");
			 pw.println("<br> Why do u want to marry:: <input type='text' name='f2t2'><br>");
			 //add form1/req1 data in form2 as hidden box value..
			 pw.println("<input type='hidden' name='hname' value='"+name+"'>");
			 pw.println("<input type='hidden' name='hfname' value='"+fname+"'>");
			 pw.println("<input type='hidden' name='hms' value='"+ms+"'>");
			 pw.println("<input type='submit' value='submit'>");
			 pw.println("</form>");
		 }//else
		 
		 //close stream 
		 pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
}//class
