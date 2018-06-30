package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class InputsCheckFilter implements Filter {
	static{
		System.out.println("InputsCheckFilter::static block");
	}
	
    public InputsCheckFilter() {
		System.out.println("InputsCheckFilter::0-param constructor");
	}
    
    @Override
    public void init(FilterConfig fg) throws ServletException {
    	System.out.println("InputsCheckFilter::init(-) method");
    	System.out.println("p1 init param value::"+fg.getInitParameter("p1"));
    }
    

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("InputsCheckFilter::doFilter(-,-)");
		int val1=0,val2=0;
		PrintWriter pw=null;
		
		//read form data
		val1=Integer.parseInt(req.getParameter("t1"));
		val2=Integer.parseInt(req.getParameter("t2"));
		//check inputs
		if(val1<=0 || val2<=0){
			System.out.println("Filter blocking request--->");
			pw=res.getWriter();
			res.setContentType("text/html");
			pw.println("<h1 style='color:red'>Invalid Inputs</h1>");
			pw.println("<a href='form.html'>Try Again</a>");
			return;
		}
		else{
			System.out.println("InputsCheckFilter::before chain.doFilter(-,-)");
			chain.doFilter(req,res);
			System.out.println("InputsCheckFilter::after chain.doFilter(-,-)");
		}
	}//doFilter(-,-)
	
	@Override
	public void destroy() {
	  System.out.println("InputsCheckFilter::destroy() method");
	}//destroy()
}//class
