package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.Tour;
import user.TourDao;

@WebServlet("/project/tour")
public class TourController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	TourDao td = new TourDao();
	
	List<Tour> ltd = td.getTourList();
	
	
req.getRequestDispatcher("/WEB-INF/Project/tour.jsp").forward(req, resp);
}
}
