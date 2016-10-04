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
		String readerID = "";// ����IDӦ�ڵ�¼ʱ����SESSION�У��˴�����һ������ID
		// ��SESSION�л���û��Ĺ��ﳵ
		List<NovelBean> novelList = (List) session.getAttribute("cart");
		LoginBean login = (LoginBean) session.getAttribute("login");
		if (login != null) {
			ReaderService service = new ReaderService();
			readerID = service.getReaderID(login.getUsername());
		}
		if (readerID == null) {
			System.out.println("�¶����Ķ���idΪ��");
			return;
		}

		NovelService service = new NovelService();
		boolean result = service.CreateOrder(novelList, readerID);// ��������

		if (result) {
			session.removeAttribute("cart");// ��չ��ﳵ
			response.getWriter().print("�������ɳɹ�,2���Զ���ת����ѯҳ��");
		} else {
			response.getWriter().print("��������ʧ��,2���Զ���ת����ѯҳ��");
		}
		response.setHeader("refresh", "2;url=QueryNovel.jsp");

	}

}
