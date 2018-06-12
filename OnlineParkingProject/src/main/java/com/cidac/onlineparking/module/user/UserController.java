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
	UserService service = new UserService();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession(false);
		// if(httpSession!=null) {
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

			this.getAreaSlot(req.getParameter("areaId"), resp);
		}

		// }else {
		// throw new IllegalAccessError("you must login first");
		// }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String link = req.getParameter("link");
		if ((link != null) && link.equals("plink")) {
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			String requestData = buffer.toString();
			System.out.println("requestData-" + requestData);

			this.bookSlot(req.getParameter("areaId"), resp);
		}
		if ((link != null) && link.equals("reg")) {
			boolean flag = true;
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			String requestData = buffer.toString();
			System.out.println("data-" + requestData);

			/* RegisterVO vo =JsonUtil.convertJsonToJava(requestData, RegisterVO.class); */
			this.registeUser(JsonUtil.convertJsonToJava(requestData, RegisterVO.class), resp);

		}
		if ((link != null) && link.equals("login")) {
			boolean flag = true;
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			String requestData = buffer.toString();
			this.login(JsonUtil.convertJsonToJava(requestData, RegisterVO.class), resp, req);

		}

	}

	private void login(RegisterVO registerVO, HttpServletResponse resp, HttpServletRequest req) {
		RegisterVO vo = service.login(registerVO);
		if (vo.getId() != 0) {
			
			System.out.println("login true");
			resp.setContentType("Application/Json");
			try {
				resp.getWriter().write(JsonUtil.convertJavaToJson(vo));
			} catch (IOException e) {

				e.printStackTrace();
			}
			/*try {
				System.out.println("login success");
				req.getRequestDispatcher("/user/user-main.html").forward(req, resp);
			} catch (ServletException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}*/
		}else {
			System.out.println("login fail");
			resp.setContentType("Application/Json");
			try {
				String status="false";
				resp.getWriter().write(status);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	private void registeUser(RegisterVO vo, HttpServletResponse resp) {

		resp.setContentType("Application/Json");
		try {
			boolean val = service.registerUser(vo);
			resp.getWriter().write(val + "");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void bookSlot(String parameter, HttpServletResponse resp) {
		String val = "true";
		resp.setContentType("Application/Json");
		try {
			resp.getWriter().write(val);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void getAreaSlot(String parameter, HttpServletResponse resp) {
		String getAreaSlot = JsonUtil.convertJavaToJson(service.getAreaSlot(Integer.parseInt(parameter)));
		System.out.println("areaName--" + getAreaSlot);
		resp.setContentType("Application/Json");
		try {
			resp.getWriter().write(getAreaSlot);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void getArea(String parameter, HttpServletResponse resp) throws NumberFormatException, IOException {
		String areaName = JsonUtil.convertJavaToJson(service.getArea(Integer.parseInt(parameter)));
		System.out.println("areaName--" + areaName);
		resp.setContentType("Application/Json");
		resp.getWriter().write(areaName);
	}

	private void getCity(HttpServletResponse resp) throws IOException {
		String city = JsonUtil.convertJavaToJson(service.selectCity());
		System.out.println(city);
		resp.setContentType("Application/Json");
		resp.getWriter().write(city);

	}

}
