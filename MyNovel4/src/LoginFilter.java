import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();

		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		// System.out.println(path);

		// 从session里取员工工号信息
		LoginBean loginBean = (LoginBean) session.getAttribute("login");

		// 登陆页面无需过滤
		if (path.indexOf("/mylogin.jsp") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		// 判断如果没有取到登陆信息,就跳转到登陆页面
		if (loginBean == null || "".equals(loginBean)) {
			// 跳转到登陆页面
			response.getWriter().print("您还没登陆，无权访问次网页");
		} else {
			// 已经登陆,继续此次请求
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
