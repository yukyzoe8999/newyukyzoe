package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/project/Logout")
public class LogoutController extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset=UTF-8");
    HttpSession session = req.getSession();
    session.invalidate();
    PrintWriter out = resp.getWriter();
    out.println("<script>alert('로그아웃 되셨습니다.'); location.href='/Personal_Project/project/main';</script>");
    out.flush();
    
    		}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset=UTF-8");
    HttpSession session = req.getSession();
    session.invalidate();
    PrintWriter out = resp.getWriter();
    out.println("<script>alert('로그아웃 되셨습니다.'); location.href='/Personal_Project/project/main';</script>");
    out.flush();
	}
}
