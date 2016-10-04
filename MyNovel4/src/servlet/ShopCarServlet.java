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
		// ��SESSION�л���û��Ĺ��ﳵ
		List<NovelBean> cart = (List) session.getAttribute("cart");
		float total = 0;// �����ܼ�
		if (cart != null) {
			for (int i = 0; i < cart.size(); i++) {
				total += cart.get(i).getPrice();
			}
		}

		// ���ﳵҳ�淵�ع��ﳵ�Ķ���novelList,��EL���ʽ��ʾ
		request.setAttribute("cartNovelList", cart);
		request.setAttribute("total", total);
		System.out.println(total);
		// ת�����ﳵҳ��
		request.getRequestDispatcher("/ShopCar.jsp").forward(request, response);
	}

}
