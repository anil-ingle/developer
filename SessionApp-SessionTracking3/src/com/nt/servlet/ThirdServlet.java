package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ThirdServlet extends HttpServlet {
  private static final String INSERT_STUD_QUERY="INSERT INTO INFO VALUES(?,?,?,?,?,?,?)";	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  PrintWriter pw=null;
		  String city=null;
		  long sal=0;
		  String name=null,addrs=null,skill=null;
		  int age=0,exp=0;
		  HttpSession ses=null;
		  Connection con=null;
		  PreparedStatement ps=null;
		  int result=0;
		 //general settings
		  pw=res.getWriter();
		  res.setContentType("text/html");
		  //read form3/req3 data
		  city=req.getParameter("city");
		  sal=Long.parseLong(req.getParameter("sal"));
		  //get Access to HttpSession object
		  ses=req.getSession(false);
		  //read form1/req1 and form2/req2 data from Session obj as session attribute values
		  name=(String)ses.getAttribute("name");
		  addrs=(String)ses.getAttribute("addrs");
		  age=(Integer)ses.getAttribute("age");
		  skill=(String)ses.getAttribute("skill");
		  exp=(Integer)ses.getAttribute("exp");
		  //write jdbc code  to insert record(all 3forms data)
		  try{
			 //register jdbc driver
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			  //create PreparedStatement object
			  ps=con.prepareStatement(INSERT_STUD_QUERY);
			  //set values to Query params
			  ps.setString(1, name);
			  ps.setString(2, addrs);
			  ps.setInt(3,age);
			  ps.setString(4, skill);
			  ps.setInt(5,exp);
			  ps.setString(6,city);
			  ps.setLong(7,sal);
			  //execute the Query
			  result=ps.executeUpdate();
			  //process the result
			  if(result==0)
				  pw.println("<h1>Registration failed </h1>");
			  else
				  pw.println("<h1>Registration succeded </h1>");
			  
			  pw.println("<br><b>Session Id::"+ses.getId()+"</b>");
			  //invalidate the Session
			  //fses.invalidate();
			  //add hyperlink
			  pw.println("<a href='personal.html'>home</a>");
			  
				pw.println("<h3 style='color:red'>Req count::"+req.getServletContext().getAttribute("reqCount")+"</h3>");

		  }//try
		  catch(SQLException se){
			  se.printStackTrace();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
		  finally{
			 try{
			 if(ps!=null)
				 ps.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			 try{
			 if(con!=null)
				 con.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
		  }//finally
		  
			  //close streams
			  pw.close();
		  }//doGet(-,-)
	
	  @Override
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		    doGet(req,res);
		}//doPost(-,-)
		  

}//class
