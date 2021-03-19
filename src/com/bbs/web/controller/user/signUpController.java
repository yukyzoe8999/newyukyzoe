package com.bbs.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.User;
import user.UserDao;

@WebServlet("/user/signUp")
public class signUpController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
		String id = req.getParameter("id");
		String pass = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		User us = new User();
		us.setID(id);
		us.setPASS(pass);
		us.setNAME(name);
		us.setEMAIL(email);
		
		UserDao ud = new UserDao();
		ud.join(us);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/view/user/signUp.jsp").forward(req, resp);
	}

}
