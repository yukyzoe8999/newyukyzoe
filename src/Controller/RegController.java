package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import user.Board;
import user.BoardDao;

@MultipartConfig(
		// location = "/tmp", //어디에 저장될 건지
		fileSizeThreshold = 1024 * 1024, //
		maxFileSize = 1024 * 1024 * 50, // 최대 업로드 가능 용량
		maxRequestSize = 1024 * 1024 * 50 * 5 // 전체 요청에 대한 파일 용량
)

@WebServlet("/project/Reg")
public class RegController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/Project/Reg.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		  req.setCharacterEncoding("UTF-8");
		   resp.setContentType("text/html; charset=UTF-8");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String open = req.getParameter("open");
		boolean pub = false;
		if (open != null)
			pub = Boolean.parseBoolean(open);

		Collection<Part> parts = req.getParts();
		StringBuilder builder = new StringBuilder();

		for (Part p : parts) {
			if (!p.getName().equals("file"))
				continue;
			if (p.getSize() == 0)
				continue;

			Part filePart = p;
			String fileName = p.getSubmittedFileName();

			builder.append(fileName);
			builder.append(",");

			InputStream fis = p.getInputStream(); // 값이 더이상 없으면 -1을 리턴 eg.indexOf

			String realPath = req.getServletContext().getRealPath("/upload");
			System.out.println("realPath : " + realPath);
			String filePath = realPath + File.separator + fileName;
			System.out.println("filePath : " + filePath);

			File fl = new File(realPath);
			if (!fl.exists())
				fl.mkdirs();

			FileOutputStream fos = new FileOutputStream(filePath); // stream 의 단위는 byte

			byte[] buf = new byte[1024]; // 한번에 1024byte씩 올림으로 수정
			int size = 0;
			// int b = fis.read();

			// 3항연산자 /연산 순서 기억
			while ((size = fis.read(buf)) != -1) // ~read = 1byte씩 올림
				fos.write(buf, 0, size);

			fos.close();
			fis.close();
		}

		builder.delete(builder.length() - 1, builder.length());

		System.out.println("builder:" + builder.toString());

		System.out.println("pub : " + pub);

		int result = 0;

		Board nt = new Board();

		nt.setTitle(title);
		nt.setContent(content);
		nt.setWriterID("최유경");
		nt.setFiles(builder.toString());
		nt.setPub(pub);

		BoardDao ns = new BoardDao();
		result = ns.insertBoard(nt);

		resp.sendRedirect("board");
//	      resp.setCharacterEncoding("UTF-8");
//	      resp.setContentType("text/htnl; charset = UTF-8");
//	      PrintWriter out = resp.getWriter();
//	      out.print("title " +title +"  ");
//	      out.print("content " +content+"  ");
//	      out.print("open " +open+"  ");
	}
}
