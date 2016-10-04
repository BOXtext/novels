import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {
	String encoding = null;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		encoding = null;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (encoding != null) {
			// ����request�ַ�����
			request.setCharacterEncoding(encoding);
			// ����response�ַ�����
			response.setContentType("text/html;charset=" + encoding);
		}
		// ���ݸ���һ��������
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding = filterConfig.getInitParameter("encoding");

	}

}
