package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecondServlet extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String skill=null;
		int exp=0;
		HttpSession ses=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form2/req2 data
		skill=req.getParameter("skill");
		exp=Integer.parseInt(req.getParameter("exp"));
		//get Access to Session
		ses=req.getSession(false);
		//write form2/req2 data Session obj as ses attributes
		ses.setAttribute("skill",skill);
		ses.setAttribute("exp", exp);
		//Generate form3 dynamically
		pw.println("<h1 style='color:red;text-align:center'>Naukri.com-->Registration page3</h1>");
		pw.println("<form action='turl' method='get'>");
		pw.println("Preffered City::<input type='text' name='city'><br>");
		pw.println("Expected Salary::<input type='text' name='sal'><br>");
		pw.println("<input type='hidden' name='cToken' value="+ses.getAttribute("sToken")+">");
		pw.println("<input type='submit' value='submit'>");
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
