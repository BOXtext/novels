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

		// ����û������URI
		String path = servletRequest.getRequestURI();
		// System.out.println(path);

		// ��session��ȡԱ��������Ϣ
		LoginBean loginBean = (LoginBean) session.getAttribute("login");

		// ��½ҳ���������
		if (path.indexOf("/mylogin.jsp") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		// �ж����û��ȡ����½��Ϣ,����ת����½ҳ��
		if (loginBean == null || "".equals(loginBean)) {
			// ��ת����½ҳ��
			response.getWriter().print("����û��½����Ȩ���ʴ���ҳ");
		} else {
			// �Ѿ���½,�����˴�����
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
