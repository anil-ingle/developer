package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ConnPoolServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw=null;
        String table=null;
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        ResultSetMetaData rsmd=null;
        int colCount=0;
		//general settings
        pw=res.getWriter();
        res.setContentType("text/html");
        //read form data
        table=req.getParameter("table");
        try{
        //get Connection obj from jdbc con pool
         con=makeConnection();
        //create Statement object
         st=con.createStatement();
         //send and execute SQLQuery in DB s/w
         rs=st.executeQuery("select * from  "+table);
         //get ResultSet MetaData
         rsmd=rs.getMetaData();
         //get col count
         colCount=rsmd.getColumnCount();
         pw.println("<table border='1'>");
         pw.println("<tr>");
         //print col names
         for(int i=1;i<=colCount;++i){
        	 pw.println("<th>"+rsmd.getColumnLabel(i)+"</th>");
         }
         pw.println("</tr>");
         // process the ResultSet
         while(rs.next()){
        	 pw.println("<tr>");
        	 for(int i=1;i<=colCount;++i){
        		 pw.println("<td>"+rs.getString(i)+"</td>");
        	 }
        	 pw.println("</tr>");
         }//while
         pw.println("</table>");
         //add hyperlink
         pw.println("<a href='input.html'>home</a>");
        }//try
        catch(SQLException se){
        	pw.println("<span style='color:red'>Table not found</span>");
        	se.printStackTrace();
        }
        catch(NamingException ne){
        	pw.println("<span style='color:red'>DataSource problem</span>");
        	ne.printStackTrace();
        }
        catch(Exception e){
        	pw.println("<span style='color:red'>Unknown Problem</span>");
        	e.printStackTrace();
        }
        finally{
        	try{
        	 if(rs!=null)
        		 rs.close();
        	}
        	catch(SQLException se){
        		se.printStackTrace();
        	}
        	try{
           	 if(st!=null)
           		 st.close();
           	}
           	catch(SQLException se){
           		se.printStackTrace();
           	}
        	try{
           	 if(con!=null)
           		 con.close();  //releases the con obj back to con pool
           	}
           	catch(SQLException se){
           		se.printStackTrace();
           	}
        	try{
              	 if(pw!=null)
              		 pw.close();  //releases the con obj back to con pool
              	}
              	catch(Exception se){
              		se.printStackTrace();
              	}
        }//finally
		
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}

	private Connection makeConnection() throws SQLException, NamingException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		// establish connection with Jndi Registry
		ic = new InitialContext();
		// get DataSource obj ref through lookup operation
		ds = (DataSource) ic.lookup("java:/comp/env/DsJndi");
		// get the con object from jdbc con pool
		con = ds.getConnection();
		return con;
	}// makeConnection()

}
