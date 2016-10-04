package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BrowserNovelListBean;
import bean.LoginBean;
import db.service.AuthorService;
import db.service.ListBookService;
import db.service.ReaderService;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�û�������û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String state = request.getParameter("state");
		// ���û���Ϣ�����½bean
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		PrintWriter print = response.getWriter();
		// ��ȡ�û���id
		// ���ݲ�ͬ�˵�½ ��ת��ͬ����
		if (state.equals("writter")) {

			AuthorService authorService = new AuthorService();
			boolean login = authorService.isLogin(loginBean);
			if (login) {
				print.write("��½�ɹ���2����Զ���ת");
				request.getSession().setAttribute("login", loginBean);
				
				response.setHeader("refresh", "2;url=AuthorLoginSuccess.jsp");
			} else {
				print.write("��½ʧ�ܣ�������");
			}
		} else if (state.equals("reader")) {
			ReaderService readerService = new ReaderService();
			boolean login = readerService.isLogin(loginBean);
			if (login) {
				request.getSession().setAttribute("login", loginBean);
				print.write("��½�ɹ���2����Զ���ת");
				response.setHeader("refresh", "2;url=ReaderLoginSuccess.jsp");
			} else {
				print.write("��½ʧ�ܣ�������");
			}
		} else if (state.equals("manager")) {
			if (username.equals("sa") && password.equals("123")) {
				print.write("��½�ɹ���2����Զ���ת");
				response.setHeader("refresh", "2;url=ManagerLoginSuccess.jsp");
			} else {
				print.write("��½ʧ�ܣ�������");
			}
		}
	}

}
