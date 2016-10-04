package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.NovelBean;

public class RemoveNovelServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<NovelBean> cart = (List) session.getAttribute("cart");
		String NovelID = request.getParameter("NovelID");
		Integer id = Integer.parseInt(NovelID);
		if (cart != null) {
			for (int i = 0; i < cart.size(); i++) {
				if (cart.get(i).getNovelID() == id) {
					cart.remove(i);
				}
			}
		}
		request.getRequestDispatcher("/ShopCar.jsp").forward(request, response);
	}

}
