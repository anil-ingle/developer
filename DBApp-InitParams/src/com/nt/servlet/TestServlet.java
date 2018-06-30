package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		ServletConfig cg=null;
		ServletContext sc=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		// Access SErvletConfig object
		cg=getServletConfig();
		pw.println("dbuser init parameter value::"+cg.getInitParameter("dbuser"));
		
		//Access SErvletcontext object
		sc=getServletContext();
		pw.println("Servletcontext obj class name::"+sc.getClass());
		
		pw.println("Using ServletContext objs we can get");
		pw.println("<br> Server Info ::"+sc.getServerInfo());
		pw.println("<br> path of input.html"+sc.getRealPath("/input.html"));
		pw.println("<br> path of web application"+sc.getRealPath("/"));
		pw.println("<br> servlet api version::"+sc.getMajorVersion()+"."+sc.getMinorVersion());
		pw.println("<br> MIME type of input.html"+sc.getMimeType("input.html"));
		pw.println("<br> Context path of the webapplication"+sc.getContextPath());
		pw.println("<br> Servletcontext name"+sc.getServletContextName());
		pw.println("<br> effective version"+sc.getEffectiveMajorVersion()+"."+sc.getEffectiveMinorVersion());
		pw.println("<br> Vitual server name:"+sc.getVirtualServerName());
		
		//write log messages
		sc.log("date and time"+new Date());
		//close stream
		pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}

}
