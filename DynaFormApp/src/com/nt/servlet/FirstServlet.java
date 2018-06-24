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
		//handle not-selecting state of checkbox
		if(ms==null)
			ms="single";
		//Generte form2 dynamically based on checkbox that is selected
		if(ms.equalsIgnoreCase("married")){
			pw.println("<h1 style='color:red;text-align:center'> Provide Marrige Life Details </h1>");
			pw.println("<form action='surl' method='post'>");
			pw.println("Spouse Name::<input type='text' name='f2t1'><br>");
			pw.println("No.of Kids::<input type='text' name='f2t2'><br>");
			pw.println("<input type='submit' value='continue'>");
			pw.println("</form>");
		}
		else{
			pw.println("<h1 style='color:red;text-align:center'> Provide Bachelor Life Details </h1>");
			pw.println("<form action='surl' method='post'>");
			pw.println("When do u want to marry::<input type='text' name='f2t1'><br>");
			pw.println("Why do u want to marry::<input type='text' name='f2t2'><br>");
			pw.println("<input type='submit' value='continue'>");
			pw.println("</form>");	
		}//else
		
		//close stream
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}

}
