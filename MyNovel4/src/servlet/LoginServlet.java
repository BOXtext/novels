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
		// 获取用户输入的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String state = request.getParameter("state");
		// 将用户信息放入登陆bean
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		PrintWriter print = response.getWriter();
		// 获取用户的id
		// 根据不同人登陆 跳转不同界面
		if (state.equals("writter")) {

			AuthorService authorService = new AuthorService();
			boolean login = authorService.isLogin(loginBean);
			if (login) {
				print.write("登陆成功，2秒后自动跳转");
				request.getSession().setAttribute("login", loginBean);
				
				response.setHeader("refresh", "2;url=AuthorLoginSuccess.jsp");
			} else {
				print.write("登陆失败，请重试");
			}
		} else if (state.equals("reader")) {
			ReaderService readerService = new ReaderService();
			boolean login = readerService.isLogin(loginBean);
			if (login) {
				request.getSession().setAttribute("login", loginBean);
				print.write("登陆成功，2秒后自动跳转");
				response.setHeader("refresh", "2;url=ReaderLoginSuccess.jsp");
			} else {
				print.write("登陆失败，请重试");
			}
		} else if (state.equals("manager")) {
			if (username.equals("sa") && password.equals("123")) {
				print.write("登陆成功，2秒后自动跳转");
				response.setHeader("refresh", "2;url=ManagerLoginSuccess.jsp");
			} else {
				print.write("登陆失败，请重试");
			}
		}
	}

}
