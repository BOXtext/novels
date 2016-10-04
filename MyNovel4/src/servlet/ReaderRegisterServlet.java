package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.service.ReaderService;
import bean.ReaderBean;
import bean.ReaderRegisterBean;

public class ReaderRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�û���������
		String readerName = request.getParameter("ReaderName");
		String userName = request.getParameter("UserName");
		String password = request.getParameter("Password");
		String password2 = request.getParameter("Password2");
		String sex = request.getParameter("Sex");
		// ����ע��bean
		ReaderRegisterBean readerRegisterBean = new ReaderRegisterBean();
		readerRegisterBean.setReaderName(readerName);
		readerRegisterBean.setPassword(password);
		readerRegisterBean.setPassword2(password2);
		readerRegisterBean.setSex(sex);
		// ����û�Ϊ��д��Ҫ��Ϣ��������ת ������д����Ϣ
		if (!readerRegisterBean.validate()) {
			System.out.println("������д���Ҫ��Ϣ");
			request.setAttribute("readerRegisterBean", readerRegisterBean);
			request.getRequestDispatcher("/ReaderRegister.jsp").forward(
					request, response);

			return;
		}
		// ����bean
		ReaderBean readerBean = new ReaderBean();
		readerBean.setReaderName(readerName);
		readerBean.setUserName(userName);
		readerBean.setPassword(password);
		readerBean.setSex(sex);
		readerBean.setBirthday(null);
		readerBean.setStatus("����");
		readerBean.setLastLoginTime(null);
		// ��������Ϣ�����ݿ�ƥ��
		ReaderService readerService = new ReaderService();
		boolean result = readerService.ReaderRegister(readerBean);
		if (!result) {
			request.setAttribute("DBMes", "��ע����û��Ѵ���");
			request.setAttribute("readerRegisterBean", readerRegisterBean);
			request.getRequestDispatcher("/ReaderRegister.jsp").forward(
					request, response);
			return;
		}
		response.getWriter().print("��ϲ��ע��ɹ���������Զ���ת");
		request.getSession().setAttribute("readerBean", readerBean);
		response.setHeader("refresh", "3;url=ReaderRegisterSuccess.jsp");

	}

}
