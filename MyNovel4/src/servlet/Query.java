package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Query extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		
		String json = null;//
		 trans1 tran = new trans1();  
		 ObjectMapper mapper = new ObjectMapper();  
		 tran.setInner("Ϧ�����¡� Ϫˮ����,�ƴ����,��ͯ��ţ,���Ѷ��С� ��կ,�Ҽ�������,��Լ�������� ��;,ˮ����,��������˺Ǻ�Ц��,����ͯߺ���š� ����һ����͵�ũ�Ҿ��󡣡� ����ű�,������Ӱ��������,�������ŵ���,������Ŀ���,���Ʋ���,Ϊ��֮�˿�����ǰһĻ,Ц�������� ����λ����·��?�� �����������������,�к������� ��·��?��Ϊ��֮�˿���˵��,�ߵ����,����ͷ,���������ס��һλ�����,��Ҳ����?�� ������˵�����?����������,���Ծ�ϲ,�����������ҷ��ӵ�?������������?�� ������?��Ϊ��֮��ҡ��ҡͷ,�������Ǵ���Ҫ����!�� ������?��������ɫ����,����������,�ͼ�ǰ������ٿ��һ����,�촽��������,ת�۾���һ�˸���,����ͨ��һƬ! �ȳ��˱�! ����δ����,��������˹����� ����! ��������,Ϊ������΢΢����,�Դ����ɾ޴���ͷ,����׽�,��Ѫ����������� ��������!�� ������д��Դ���,ȴ����ͯתͷ,���������׻��ǡ��ǿ����˵�һĻ,��������,�����µ�ˮţ��Ҳ���˴̼�,�����衱����,������̤,�����ӷɱ���ȥ�� ���ܵ���ô?�� ��ͷ���³�һ�������Ѫ�Ĺ�ͷ,һ����,����˺ٺ���Ц,��������,¶��Ұ��ģ��,����������ȥ�� �!�!�! ��������,�Ѹ�������ͯ�� �ҽС���С�����,��ͯ���˴�ţ����ʳ,����һ����Ѫ�����,����������Ⱦ���ˡ� ����,���˲���ͣ��,���˴�կ�� �������Ӷ�ʱ����,����ŭ��,Ů�˰����� Ϧ�����,��ϼ����,��͸����졣 ��ͷ�˵��Դ�����Ϊ������С,��߲�����һĨ��Ѫ,һ��һ��������ȥ�� ����һ��,����һͷ��Ӵ����ܳ�,��ͷ���ԡ���̬ӷ��,���н��ٶ�,���������ܵ���ͷ����ǰ�� ������,��������¶����,մ����Ѫ����Ͱ�������,�������󱻰���Ū���ˡ��� ����?����ͷ�ϵ���ü���˰����� �����ְ�!���ְ�!����ͷҡ������,������û�����϶���,����ߴ�ﹾ���˵��һ��,Ȼ��һͷײ����!�� ��ײ����?����ͷ����¶�ɻ�,��ת��һ��,������,һ�������,��������,��ô������,�۹����˳�Ҳ������,��Χ���м�������,�����˲�����,��ץ�������� �����������˿���,�������뵽һ��,��æ��:����������и�����,����ȥһ���˲�,���������ǿ����!�� ������?����ͷ�˵��ͷ,��ץ��,�º��һ��,���޹�������,�����˳���,��������ԡʥ�˽̻�,�����Ǽ��ֵġ��� ���Ļ�������,ǰ��ʹ�����������,������,һ��ľ�����ӴҴ�����:������!������!����ŵĶ�������!");
		 tran.setCharptName("��һ�� ������ë");
		 json = mapper.writeValueAsString(tran);//����һ��json�������ڴ�novelName��ֵ
		 out.write(json);
		out.flush();
		out.close();
	}

}
