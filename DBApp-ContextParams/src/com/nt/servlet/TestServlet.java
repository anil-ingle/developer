package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	
	public TestServlet() {
		/*ServletConfig cg=getServletConfig();
		ServletContext sc=getServletContext();
		System.out.println("p1 init value::"+cg.getInitParameter("p1"));
		System.out.println("dbuser context value::"+sc.getInitParameter("dbuser"));*/
	}
	
	public void init(){
		ServletConfig cg=getServletConfig();
		ServletContext sc=getServletContext();
		System.out.println("p1 init value::"+cg.getInitParameter("p1"));
		System.out.println("dbuser context value::"+sc.getInitParameter("dbuser"));
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		ServletContext sc=null;
		ServletConfig cg=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		// Access ServletContext object
		sc=getServletContext();
		pw.println("dbuser context parameter value::"+sc.getInitParameter("dbuser"));
		
		pw.println("ServletContext obj class name::"+sc.getClass());
		
		//Access Servletconfig object
		cg=getServletConfig();
		pw.println("<br> p1 init Param value::"+cg.getInitParameter("p1"));
		pw.println("<br> dbuser init Param value::"+cg.getInitParameter("dbuser"));
		//close stream
		pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}

}
