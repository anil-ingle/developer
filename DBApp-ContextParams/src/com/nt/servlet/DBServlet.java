package com.nt.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*; //jdbc api

public class  DBServlet extends HttpServlet
{ 
	private static final String EMP_DETAILS_BY_NO="SELECT  EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO=?";
	Connection con=null;
	PreparedStatement ps=null;


	public void init(){
		ServletContext sc=null;
		String driver=null,url=null,dbuser=null,dbpwd=null;
		//Access SevletContext object
         sc=getServletContext();
		 driver=sc.getInitParameter("driver");
		 url=sc.getInitParameter("url");
		 dbuser=sc.getInitParameter("dbuser");
		 dbpwd=sc.getInitParameter("dbpwd");

		try{
			//register jdbc driver
			Class.forName(driver);
			//establish the connection
			con=DriverManager.getConnection(url,dbuser,dbpwd);
            //create PreparedStatement object
           ps=con.prepareStatement(EMP_DETAILS_BY_NO);
      	}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }//init()

	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		 PrintWriter pw=null;
		 ResultSet rs=null;
		 int eno=0;
        //get PrintWriter 
		pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		try{
		//read form data
		eno=Integer.parseInt(req.getParameter("teno"));
		//set value to Query parameter
		ps.setInt(1,eno);
		//execute the Query
		rs=ps.executeQuery();
		//process the ResultSet
		if(rs.next()){
			pw.println("<h1 style='color:red'> Employee Details  are </h1>");
            pw.println("<br><b>Emp number:::"+eno);
			pw.println("<br><b> Emp name:::"+rs.getString(2));
			pw.println("<br> <b> Emp Job :::"+rs.getString(3));
			pw.println("<br> <b> Emp Salary:::"+rs.getInt(4));
		}
		else{
             pw.println("<br> <h1 style='color:red'>Records not found</h1>");
		}
		//get Access to ServletConfig object
		ServletConfig cg=getServletConfig();
		pw.println("<br> servlet logical name"+cg.getServletName());
        pw.println("<br> ServletConfig object class name"+cg.getClass());
		pw.println("<br> p1 init Param value::"+cg.getInitParameter("p1"));
		
		//add hyperlink
		pw.println("<br><a href='input.html'>home</a>");
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(pw!=null)
					pw.close();
			}//try
			catch(Exception ioe){
				ioe.printStackTrace();
			}
		}//finally
	}//doGet(-,-)

	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
         doGet(req,res);
    }//doPost(-,-)

	public void destroy(){
          //close jdbc objs
		  try{
			  if(ps!=null)
				  ps.close();
		  }//try
		  catch(SQLException se){
			   se.printStackTrace();
		  }

		  try{
			  if(con!=null)
				  con.close();
		  }//try
		  catch(SQLException se){
			   se.printStackTrace();
		  }
	}//destroy()
}//class
