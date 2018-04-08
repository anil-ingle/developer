package com.cidac.onlineparking.module.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cidac.onlineparking.utilty.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@WebServlet("/user")
public class UserController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession(false);
		// if(httpSession!=null) {
		String link1=req.getParameter("cid");
		if ((link1!=null)&&link1.equals("cityLink")) {
			this.getCity(resp);
		}
		String link2 = req.getParameter("link2");
		if ((link2 != null) && link2.equals("cityAreaLink")) {
			this.getArea(req.getParameter("cityId"), resp);
		}
		// }else {
		// throw new IllegalAccessError("you must login first");
		// }
	}

	private void getArea(String parameter, HttpServletResponse resp) throws NumberFormatException, IOException {
		String areaName = JsonUtil.convertJavaToJson(new UserService().getArea(Integer.parseInt(parameter)));
		resp.setContentType("Application/Json");
		resp.getWriter().write(areaName);
	}

	private void getCity(HttpServletResponse resp) throws IOException {
		String city = JsonUtil.convertJavaToJson(new UserService().selectCity());
		System.out.println(city);
		resp.setContentType("Application/Json");
		resp.getWriter().write(city);

	}

}
