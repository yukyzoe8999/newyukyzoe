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

@WebServlet("/project/Join")
public class JoinController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	      resp.setContentType("text/html; charset=UTF-8");
		String id = req.getParameter("ID");
		String pass = req.getParameter("PASS");
		String name = req.getParameter("NAME");
		String email = req.getParameter("EMAIL");
		 
		User us = new User();
		us.setID(id);
		us.setPASS(pass);
		us.setNAME(name);
		us.setEMAIL(email);
		
		UserDao ud = new UserDao();
		int result = ud.join(us);
		
		System.out.println("id : "+id);
		
		
		if (result == -1) {
			HttpSession session = req.getSession(true);
			session.setAttribute("User", result);
			session.setAttribute("id", id);
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('다시 시도해주세요.'); location.href='/Personal_Project/project/Join';</script>");

			out.flush();
		} else  {
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('회원가입 성공!'); location.href='/Personal_Project/project/Login';</script>");

			out.flush();
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	      resp.setContentType("text/html; charset=UTF-8");
		String ff= "1";
		req.setAttribute("ff", ff);
		req.getRequestDispatcher("/WEB-INF/Project/Login.jsp").forward(req, resp);
	}

}
