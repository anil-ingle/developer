package com.cidac.onlineparking.module.user;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cidac.onlineparking.utilty.JsonUtil;

@WebServlet("/user")
public class UserController extends HttpServlet {
	/*
	 * Author:Ani D.Ingle
	 * Date:12/03/218
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService service = new UserService();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String link1 = req.getParameter("cid");
		if ((link1 != null) && link1.equals("cityLink")) {
			this.getCity(resp);
		}
		String link2 = req.getParameter("link2");
		if ((link2 != null) && link2.equals("cityAreaLink")) {
			System.out.println("link2  " + link2 + "   cityId  " + req.getParameter("cityId"));
			this.getArea(req.getParameter("cityId"), resp);
		}
		if ((link2 != null) && link2.equals("areaSlot")) {

			this.getAreaSlot(req.getParameter("areaId"), req, resp);
		}
		String link = req.getParameter("link");
		System.out.println("link--" + link);
		if ((link != null) && link.equals("logout")) {
			System.out.println("in m1");
			this.logOut(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String link = req.getParameter("link");
		if ((link != null) && link.equals("plink")) {
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null)
				buffer.append(line);
			String requestData = buffer.toString();
			this.bookSlot(buffer.toString(), req, resp);
		}
		if ((link != null) && link.equals("reg")) {
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null)
				buffer.append(line);
			this.registeUser(JsonUtil.convertJsonToJava(buffer.toString(), RegisterVO.class), resp);

		}
		if ((link != null) && link.equals("wolletlink")) {
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null)
				buffer.append(line);
			WolletBookVO bookVO = JsonUtil.convertJsonToJava(buffer.toString(), WolletBookVO.class);
			System.out.println("bookvo  " + bookVO);
			this.slotBookUsingWolet(bookVO, req, resp);

		}
		if ((link != null) && link.equals("login")) {
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null)
				buffer.append(line);
			this.login(JsonUtil.convertJsonToJava(buffer.toString(), RegisterVO.class), resp, req);

		}

	}

	// ------user defined private method to services-------------------------------
	private void login(RegisterVO registerVO, HttpServletResponse resp, HttpServletRequest req) {
		RegisterVO vo = service.login(registerVO);

		HttpSession session = req.getSession(true);
		if (vo.getId() != 0) {
			resp.setContentType("Application/Json");
			try {
				resp.getWriter().write(JsonUtil.convertJavaToJson(vo));
			} catch (IOException e) {

				e.printStackTrace();
			}

		} else {
			resp.setContentType("Application/Json");
			try {

				resp.getWriter().write("false");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	private void registeUser(RegisterVO vo, HttpServletResponse resp) {

		resp.setContentType("Application/Json");
		try {

			resp.getWriter().write(service.registerUser(vo) + "");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void bookSlot(String parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {

			resp.setContentType("Application/Json");
			try {
				resp.getWriter().write("true");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			req.getRequestDispatcher("").forward(req, resp);
		}

	}

	private void getAreaSlot(String parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {

			resp.setContentType("Application/Json");
			try {
				resp.getWriter().write(JsonUtil.convertJavaToJson(service.getAreaSlot(Integer.parseInt(parameter))));
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			req.getRequestDispatcher("").forward(req, resp);
		}

	}

	private void getArea(String parameter, HttpServletResponse resp) throws NumberFormatException, IOException {
		resp.setContentType("Application/Json");
		resp.getWriter().write(JsonUtil.convertJavaToJson(service.getArea(Integer.parseInt(parameter))));
	}

	private void getCity(HttpServletResponse resp) throws IOException {
		resp.setContentType("Application/Json");
		resp.getWriter().write(JsonUtil.convertJavaToJson(service.selectCity()));

	}

	private void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			System.out.println("in m2");

			session.invalidate();
			String val = "true";
			resp.setContentType("Application/Json");
			try {
				resp.getWriter().write(val);
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			req.getRequestDispatcher("").forward(req, resp);
		}

	}

	private void slotBookUsingWolet(WolletBookVO bookVO, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			boolean status = service.slotBookUsingWolet(bookVO);
			System.out.println("status " + status);
			resp.setContentType("Application/Json");
			try {
				resp.getWriter().write(status + "");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			req.getRequestDispatcher("").forward(req, resp);
		}

	}

}
