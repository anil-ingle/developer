package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class ZCheckBrowserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String brName=null;
		PrintWriter pw=null;
		
		//get the browsername
		brName=((HttpServletRequest)req).getHeader("user-agent");
		System.out.println(brName);
		if(brName.indexOf("Chrome")==-1){
			System.out.println("1");
			pw=res.getWriter();
			res.setContentType("text/html");
			pw.println("<h1 style='color:red'>!!!oops Request is not allowed other than from Chrome</h1>");
			pw.println("<a href='personal.html'>Try Again with Chrome</a>");
			return;
		}
		else{
			System.out.println("CBF:before chain.doFilter(-,-)");
			chain.doFilter(req,res);
			System.out.println("CBF:after chain.doFilter(-,-)");
		}
	}
}
