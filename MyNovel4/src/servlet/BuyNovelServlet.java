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
		System.out.println("����С˵");
		// ���Ҫ�����С˵ID
		String novelID = request.getParameter("NovelID");
		// ����ҵ��㣬��ȡС˵����
		NovelService service = new NovelService();
		NovelBean novelBean = service.GetNovel(novelID);
		// ��С˵����SESSION����ΪSESSION�еı������Ա����ҳ�����
		HttpSession session = request.getSession();
		// ��SESSION�л���û��Ĺ��ﳵ
		@SuppressWarnings("unchecked")
		List<NovelBean> cart = (List) session.getAttribute("cart");
		if (cart == null) {// ������״ι��򣬴���һ����������Ϊ���ﳵ
			cart = new ArrayList<NovelBean>();
			// �����ﳵ����SESSION
			session.setAttribute("cart", cart);
		}
		// �жϸ�С˵�Ƿ��ѹ���,δ��������빺�ﳵ
		if (!cart.contains(novelBean))
			cart.add(novelBean);
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).getNovelName());
		}
		// ���ѯҳ�淵�ع��ﳵ������novelCount,��EL���ʽ��ʾ
		request.setAttribute("novelCount", cart.size());
		// �ص���ѯҳ��
		request.getRequestDispatcher("/QueryNovel.jsp").forward(request,
				response);
		return;

	}

}
