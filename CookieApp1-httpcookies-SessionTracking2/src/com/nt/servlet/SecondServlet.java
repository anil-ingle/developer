package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {
	private static final String INSERT_TAX_TABLE="INSERT INTO TAX_TAB VALUES(?,?,?,?)";
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		   PrintWriter pw=null;
		   int income=0,tax=0;
		   String name=null,fname=null;
		   Cookie cks[]=null;
		   Connection con=null;
		   PreparedStatement ps=null; 
		   int result=0;
		  //general settings
		   pw=res.getWriter();
		   res.setContentType("text/html");
		  //read form2/req2 data
		   income=Integer.parseInt(req.getParameter("income"));
		   tax=Integer.parseInt(req.getParameter("tax"));
		  //read cookie values (form1/req1 data) (Session tracking)
		   cks=req.getCookies();
		   if(cks!=null){
			   name=cks[0].getValue();
			   fname=cks[1].getValue();
		   }
		   //writer both form1/req1 and form2/req2 data as record
		   try{
			//register Driver
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			 //create PRpearedStatement  object
			 ps=con.prepareStatement(INSERT_TAX_TABLE);
			 //set values to Query params
			 ps.setString(1,name);
			 ps.setString(2,fname);
			 ps.setInt(3,income);
			 ps.setInt(4,tax);
			 //execute Query
			 result=ps.executeUpdate();
			 //process the Result
			 if(result==0)
				pw.println("<h1> registration failed </h1>");
			 else
				 pw.println("<h1> registration succeded </h1>");
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
		   //dispaly dyanamic webpage having form1/req1 and form2/req2 data
		   pw.println("<br><h1> form1/req1 data::"+name+"...."+fname+"</h1>");
		   pw.println("<br><h1> form2/req2 data::"+income+"...."+tax+"</h1>");
		   //add hyperlink
		   pw.println("<a href='input.html'>home</a>");
		   //close stream
		   pw.close();
		   
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  doGet(req,res);
	}//doPost(-,-)
}//class
