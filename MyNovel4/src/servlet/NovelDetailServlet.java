package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BrowserNovelListBean;
import db.service.ListBookService;

public class NovelDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查询数据库中的小说信息 返回
		ListBookService service = new ListBookService();
		List<BrowserNovelListBean> novelList = service.getBrowserNovelList();
		request.getSession().setAttribute("novelList", novelList);
		request.getRequestDispatcher("BrowserNovel.jsp").forward(request,
				response);
		;
	}

}
