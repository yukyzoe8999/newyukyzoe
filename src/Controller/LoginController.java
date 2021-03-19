package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;
import user.UserDao;

@WebServlet("/project/Login")
public class LoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   req.setCharacterEncoding("UTF-8");
		      resp.setContentType("text/html; charset=UTF-8");
		String id = req.getParameter("id");
		String pass = req.getParameter("password");

		User us = new User();
		us.setID(id);
		us.setPASS(pass);

		UserDao ud = new UserDao();
		int result = ud.login(id, pass);

		if (result == 1) {
			HttpSession session = req.getSession(true);
			session.setAttribute("User", result);
			session.setAttribute("id", id);
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('로그인 성공.'); location.href='/Personal_Project/project/main';</script>");

			out.flush();
		} else if (result == 0) {
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('비밀번호가 틀립니다.'); location.href='/Personal_Project/project/Login';</script>");

			out.flush();
		} else if (result == -1) {
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('존재하지 않는 아이디 입니다.'); location.href='/Personal_Project/project/Login';</script>");

			out.flush();
		} else {
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('DB오류 입니다.'); location.href='/Personal_Project/project/Login';</script>");

			out.flush();
		}


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/Project/Login.jsp").forward(req, resp);
	}

}
