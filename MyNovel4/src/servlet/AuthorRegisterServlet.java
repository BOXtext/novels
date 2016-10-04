package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.service.AuthorService;
import bean.AuthorBean;
import bean.AuthorRegisterBean;

public class AuthorRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorName = request.getParameter("AuthorName");
		String userName = request.getParameter("UserName");
		String password = request.getParameter("Password");
		String password2 = request.getParameter("Password2");
		String sex = request.getParameter("Sex");

		AuthorRegisterBean authorRegisterBean = new AuthorRegisterBean();
		authorRegisterBean.setAuthorName(authorName);
		authorRegisterBean.setPassword(password);
		authorRegisterBean.setPassword2(password2);
		authorRegisterBean.setSex(sex);
		if (!authorRegisterBean.validate()) {
			System.out.println("未填写完必要信息");
			request.setAttribute("authorRegisterBean", authorRegisterBean);
			request.getRequestDispatcher("/AuthorRegister.jsp").forward(
					request, response);
			return;
		}

		AuthorBean authorBean = new AuthorBean();
		authorBean.setAuthorName(authorName);
		authorBean.setUsername(userName);
		authorBean.setPassword(password);
		authorBean.setSex(sex);
		authorBean.setBirthday(null);
		authorBean.setStatus("启用");
		authorBean.setLastLoginTime(null);

		AuthorService authorService = new AuthorService();
		boolean result = authorService.Register(authorBean);
		PrintWriter print = response.getWriter();
		if (result) {
			print.write("恭喜你注册成功，3秒后自动跳转");
			request.getSession().setAttribute("author", authorBean);
			response.setHeader("refresh", "3;url=AuthorRegisterSucess.jsp");
		} else {
			request.setAttribute("DBMes", "您注册的用户已存在");
			request.setAttribute("authorRegisterBean", authorRegisterBean);
			request.getRequestDispatcher("/AuthorRegister.jsp").forward(
					request, response);
			return;
		}

	}

}
