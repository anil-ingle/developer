package com.nt.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadServlet extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		File file=null;
		ServletContext sc=null;
		String path=null;
		InputStream is=null;
		ServletOutputStream sos=null;
		byte[] buffer=null;
		int bytesRead=0;
		String fname=null;
		//read fname req param value
		fname=req.getParameter("fname");
		//get Access to ServletContext object
		sc=getServletContext();
		//Locate the File to be downloaded
	     path=sc.getRealPath("/"+fname);
		file=new File(path);
		//get the length of the file to be downloaded and set that length as reponse content length
         res.setContentLengthLong(file.length());
        //get the MIME type of given file and set it as response content type
         res.setContentType(sc.getMimeType("/"+fname));
        //Instruct browser to make the recived content of downloadable file
         res.addHeader("Content-Disposition","attachment;fileName="+fname);
        //create InputStream pointing to the file to be downloaded
         is=new FileInputStream(file);
         //create OutputStream pointing to response object...
         sos=res.getOutputStream();
         //Use Buffer based logic write content of file to be donwloaded to
         // to response object.
         buffer=new byte[4096];
         while((bytesRead=is.read(buffer))!=-1){
        	sos.write(buffer,0,bytesRead); 
         }
         //close streams
         is.close();
         sos.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doGet(-,-)
}//class
