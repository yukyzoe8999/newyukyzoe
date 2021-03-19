package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.PhotoDao;
import user.photo;
import user.photocomment;

@WebServlet("/project/photoview")
public class photoviewController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pid = req.getParameter("pid");

//	String score_ = req.getParameter("s");
//	int score = Integer.parseInt(score_);

		int count = PhotoDao.getInstance().getcommentcount(pid);
		req.setAttribute("count", count);
		if (!(count == 0)) {
			List<photocomment> pc = PhotoDao.getInstance().getphotocommentDetail(pid);
			
			req.setAttribute("c", pc);
		}

		photo detailphoto = PhotoDao.getInstance().getphotoDetail(pid);
		req.setAttribute("p", detailphoto);

		req.getRequestDispatcher("/WEB-INF/Project/photoview.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String userid = "zoe";
		String comment = req.getParameter("comment");
		String score_ = req.getParameter("s");
		String pid = req.getParameter("pid");
		int score = Integer.parseInt(score_);

//		System.out.println("comment"+comment);
//		System.out.println("score"+score);
//		System.out.println("pid"+pid);
		
		photocomment cd = new photocomment();
		cd.setUserid(userid);
		cd.setPid(pid);
		cd.setScore(score);
		cd.setComment(comment);
		
		int result= PhotoDao.getInstance().insertConmment(cd);
		
		resp.sendRedirect("photoview?pid="+pid);

	}
}
