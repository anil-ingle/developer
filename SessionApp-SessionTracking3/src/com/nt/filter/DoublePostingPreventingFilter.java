package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(value={"/surl","/turl"})
public class DoublePostingPreventingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hreq=null;
		HttpSession ses=null;
		int clientToken=0,serverToken=0;
		PrintWriter pw=null;
		
		//typecasting
		hreq=(HttpServletRequest)req;
		if(hreq.getServletPath().equals("/surl")){
			//get Access to SeSssion
			ses=hreq.getSession();
			//gnerate ServerSide token
			ses.setAttribute("sToken",new Random().nextInt(1000));
			chain.doFilter(req,res);
		}
		else if(hreq.getServletPath().equals("/turl")){
			 //get Access to Session
			ses=hreq.getSession();
			//read client and Server Tokens
			clientToken=Integer.parseInt(req.getParameter("cToken"));
			serverToken=(Integer)ses.getAttribute("sToken");
			if(clientToken==serverToken){
				//chanage Server token value
				ses.setAttribute("sToken",new Random().nextInt(1000));
				chain.doFilter(req, res);
			}
			else{
			 pw=res.getWriter();
			 res.setContentType("text/html");
			 pw.println("<h1 style='color:red'>Double Posting not allowed here </h1>");
			 pw.println("<a href='personal.html'>home</a>");
			}
			
		}//else if
		else{
			chain.doFilter(req,res);
		}

	}

}
