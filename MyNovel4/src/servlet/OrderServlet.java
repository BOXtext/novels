package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.NovelBean;
import db.service.AuthorService;
import db.service.NovelService;
import db.service.ReaderService;

public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String readerID = "";// 读者ID应在登录时放入SESSION中，此处给定一个读者ID
		// 从SESSION中获得用户的购物车
		List<NovelBean> novelList = (List) session.getAttribute("cart");
		LoginBean login = (LoginBean) session.getAttribute("login");
		if (login != null) {
			ReaderService service = new ReaderService();
			readerID = service.getReaderID(login.getUsername());
		}
		if (readerID == null) {
			System.out.println("下订单的读者id为空");
			return;
		}

		NovelService service = new NovelService();
		boolean result = service.CreateOrder(novelList, readerID);// 创建订单

		if (result) {
			session.removeAttribute("cart");// 清空购物车
			response.getWriter().print("订单生成成功,2秒自动跳转到查询页面");
		} else {
			response.getWriter().print("订单生成失败,2秒自动跳转到查询页面");
		}
		response.setHeader("refresh", "2;url=QueryNovel.jsp");

	}

}
