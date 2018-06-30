package com.nt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class PerformanceTestFilter implements Filter {
    private long start,end;
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		start=System.currentTimeMillis();
		System.out.println("PTF:before chain.doFilter(-,-)");
		chain.doFilter(req,res);
		System.out.println("PTF:after chain.doFilter(-,-)");
		end=System.currentTimeMillis();
		//write time duration to file..
		req.getServletContext().log( ((HttpServletRequest)req).getRequestURI()+" has taken "+(end-start)+" ms to process the request" );
	}//method
}//class
