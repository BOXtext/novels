package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.NovelBean;
import db.service.NovelService;

public class BuyNovelServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("购买小说");
		// 获得要购买的小说ID
		String novelID = request.getParameter("NovelID");
		// 调用业务层，获取小说对象
		NovelService service = new NovelService();
		NovelBean novelBean = service.GetNovel(novelID);
		// 将小说放入SESSION，因为SESSION中的变量可以被多个页面访问
		HttpSession session = request.getSession();
		// 从SESSION中获得用户的购物车
		@SuppressWarnings("unchecked")
		List<NovelBean> cart = (List) session.getAttribute("cart");
		if (cart == null) {// 如果是首次购买，创建一个新链表作为购物车
			cart = new ArrayList<NovelBean>();
			// 将购物车放入SESSION
			session.setAttribute("cart", cart);
		}
		// 判断该小说是否已购买,未购买则加入购物车
		if (!cart.contains(novelBean))
			cart.add(novelBean);
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).getNovelName());
		}
		// 向查询页面返回购物车的数量novelCount,用EL表达式显示
		request.setAttribute("novelCount", cart.size());
		// 回到查询页面
		request.getRequestDispatcher("/QueryNovel.jsp").forward(request,
				response);
		return;

	}

}
