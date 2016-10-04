package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.NovelBean;

public class ShopCarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 从SESSION中获得用户的购物车
		List<NovelBean> cart = (List) session.getAttribute("cart");
		float total = 0;// 计算总价
		if (cart != null) {
			for (int i = 0; i < cart.size(); i++) {
				total += cart.get(i).getPrice();
			}
		}

		// 向购物车页面返回购物车的对象novelList,用EL表达式显示
		request.setAttribute("cartNovelList", cart);
		request.setAttribute("total", total);
		System.out.println(total);
		// 转到购物车页面
		request.getRequestDispatcher("/ShopCar.jsp").forward(request, response);
	}

}
