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

@WebServlet("/project/Photoboard")
public class PhotoboardController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<photo> newphoto = PhotoDao.getInstance().getphotoList();
		req.setAttribute("plist", newphoto);
		req.getRequestDispatcher("/WEB-INF/Project/Photoboard.jsp").forward(req, resp);
	}
}
