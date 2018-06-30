package com.nt.listeners;

import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionDurationListener implements HttpSessionListener {
     private long start,end;
	public SessionDurationListener() {
      System.out.println("SessionDurationListener::0-param constructor");
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
	  start=System.currentTimeMillis();
	  //write log file
	  se.getSession().getServletContext().log("Session started at"+new Date());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		end=System.currentTimeMillis();
	  //write log file
		se.getSession().getServletContext().log("Session complted at"+new Date()+" session duration::"+(end-start)+"ms.");
	}
}
