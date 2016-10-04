package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.NovelBean;
import db.service.NovelService;
import db.service.ReaderService;

public class QueryNovelServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String novelName = request.getParameter("novelName");// 获得小说名
		NovelService service = new NovelService();
		List<NovelBean> novelList = service.QueryNovel(novelName);
		request.setAttribute("queryNovelList", novelList);// 将图书列表传递给查询页面
		request.getRequestDispatcher("/QueryNovel.jsp").forward(request,
				response);
	}

}
