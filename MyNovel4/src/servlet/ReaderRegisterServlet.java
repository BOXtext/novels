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
		// 获取用户输入内容
		String readerName = request.getParameter("ReaderName");
		String userName = request.getParameter("UserName");
		String password = request.getParameter("Password");
		String password2 = request.getParameter("Password2");
		String sex = request.getParameter("Sex");
		// 读者注册bean
		ReaderRegisterBean readerRegisterBean = new ReaderRegisterBean();
		readerRegisterBean.setReaderName(readerName);
		readerRegisterBean.setPassword(password);
		readerRegisterBean.setPassword2(password2);
		readerRegisterBean.setSex(sex);
		// 如果用户为填写必要信息，则不予跳转 必须填写完信息
		if (!readerRegisterBean.validate()) {
			System.out.println("请您填写完必要信息");
			request.setAttribute("readerRegisterBean", readerRegisterBean);
			request.getRequestDispatcher("/ReaderRegister.jsp").forward(
					request, response);

			return;
		}
		// 读者bean
		ReaderBean readerBean = new ReaderBean();
		readerBean.setReaderName(readerName);
		readerBean.setUserName(userName);
		readerBean.setPassword(password);
		readerBean.setSex(sex);
		readerBean.setBirthday(null);
		readerBean.setStatus("启用");
		readerBean.setLastLoginTime(null);
		// 将读者信息与数据库匹配
		ReaderService readerService = new ReaderService();
		boolean result = readerService.ReaderRegister(readerBean);
		if (!result) {
			request.setAttribute("DBMes", "您注册的用户已存在");
			request.setAttribute("readerRegisterBean", readerRegisterBean);
			request.getRequestDispatcher("/ReaderRegister.jsp").forward(
					request, response);
			return;
		}
		response.getWriter().print("恭喜您注册成功，三秒后自动跳转");
		request.getSession().setAttribute("readerBean", readerBean);
		response.setHeader("refresh", "3;url=ReaderRegisterSuccess.jsp");

	}

}
