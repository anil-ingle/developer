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

public class SecondServlet extends HttpServlet {
 private static final String INSERT_QUERY="INSERT INTO HIDDEN_PERSON_TAB VALUES(?,?,?,?,?)";	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String f2val1=null,f2val2=null;
		String name=null,fname=null,ms=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form2/req2 data
		f2val1=req.getParameter("f2t1");
		f2val2=req.getParameter("f2t2");
		//read form1/req1 data form hidden boxes of form2 (Sessiontracking)
		name=req.getParameter("hname");
		fname=req.getParameter("hfname");
		ms=req.getParameter("hms");
		//write form1/req1 , form2/req2 data to Db table as record
		try{
		 //register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
          //create PReparedStatement object		
			ps=con.prepareStatement(INSERT_QUERY);
		  //set values to Query params
			ps.setString(1, name);
			ps.setString(2,fname);
			ps.setString(3,ms);
			ps.setString(4,f2val1);
			ps.setString(5,f2val2);
			//execute the Query
			result=ps.executeUpdate();
			//process the result
			if(result==0){
				pw.println("<h1> Registration failed</h1>");
			}
			else{
				pw.println("<h1> Registration succeeded</h1>");
			}
			
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
		//Display form1/req1, form2/req2 data in a dynamic webpage
		pw.println("<h1> Form1/req1 data:::"+name+"..."+fname+"..."+ms);
		pw.println("<h1> Form2/req2 data:::"+f2val1+"..."+f2val2);
		//add hyperlink
		pw.println("<a href='details.html'>home</a>");
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
}//class
