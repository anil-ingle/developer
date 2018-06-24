package com.nt.listeners;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationMaitenceListener implements ServletContextListener {
      private long start,end;
	public ApplicationMaitenceListener() {
     System.out.println("ApplicationMaitenceListener:0-param constructor");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ApplicationMaitenceListener:contextInitialized(-)");
		start=System.currentTimeMillis();
		//write to log file
		sce.getServletContext().log("SessionApp is deployed/reloaded at"+new Date());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ApplicationMaitenceListener:contextDestroyed(-)");
		end=System.currentTimeMillis();
		sce.getServletContext().log("Session App stopped/undeployed at"+
		 new Date()+"Total running duration::"+(end-start)+"ms");
	}
	
	
}
