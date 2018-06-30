package com.nt.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestDurationListener implements ServletRequestListener {
	 private long start,end;
	 
	 public RequestDurationListener() {
		System.out.println("RequestDurationListener::0-param constructor");
	}
	 
  @Override
  	public void requestInitialized(ServletRequestEvent sre) {
	  System.out.println("RDL:requestInitialized(-)");
	  start=System.currentTimeMillis();
  	}
  
  @Override
	public void requestDestroyed(ServletRequestEvent sre) {
	  System.out.println("RDL:requestDestroyed(-)");
	  HttpServletRequest hreq=null;  
	  end=System.currentTimeMillis();
	  //write request processing time to log file
	  hreq=(HttpServletRequest)sre.getServletRequest();
	  sre.getServletRequest().getServletContext().log(hreq.getRequestURI()+" has taken"+(end-start)+" ms");
	}

}
