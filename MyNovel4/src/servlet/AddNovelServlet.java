package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.AddNovelBean;
import bean.LoginBean;
import bean.NovelBean;
import db.DBHelper;
import db.service.AuthorService;

public class AddNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// form ʹ��enctype='multipart/form-data'���޷��� request.getParameter��ȡ����
		String novelName = "";// ���С˵��;
		String content = "";// ��ü��;
		String category = "";// ���С˵���;
		String pic = "";// С˵ͼƬ
		// �õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
		// String savePath =
		 //this.getServletContext().getRealPath("/WEB-INF/upload");
		String savePath = this.getServletContext().getRealPath("/")
				+"\\upload";
		System.out.println(savePath);
		File file = new File(savePath);
		// �ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "Ŀ¼�����ڣ���Ҫ����");
			// ����Ŀ¼
			file.mkdir();
		}
		// ��Ϣ��ʾ
		String message = "";
		try {
			// ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
			// 1������һ��DiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2������һ���ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			// ����ϴ��ļ�������������
			upload.setHeaderEncoding("UTF-8");

			// 3���ж��ύ�����������Ƿ����ϴ���������
			if (!ServletFileUpload.isMultipartContent(request)) {
				// ���մ�ͳ��ʽ��ȡ����
				return;
			}

			// 4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>����
			// ÿһ��FileItem��Ӧһ��Form����������
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// ���fileitem�з�װ������ͨ�����������
				if (item.isFormField()) {
					String name = item.getFieldName();
					// �����ͨ����������ݵ�������������
					String value = item.getString("UTF-8");
					System.out.println(name + "=" + value);
					// FORMʹ�ü��ܷ�ʽ���ݣ�����ͨ�� request.getParameter��ȡ������ֻ�ܱȽ�
					if (name.equals("novelName"))
						novelName = value;
					if (name.equals("content"))
						content = value;
					if (name.equals("category"))
						category = value;
					System.out.println("novelName:" + novelName);

				} else {// ���fileitem�з�װ�����ϴ��ļ�
						// �õ��ϴ����ļ����ƣ�
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺
					// c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
					// �����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
					filename = filename
							.substring(filename.lastIndexOf("\\") + 1);
					pic = filename;// ��ȡС˵ͼƬ��

					// ��ȡitem�е��ϴ��ļ���������
					InputStream in = item.getInputStream();
					// ����һ���ļ������
					FileOutputStream out = new FileOutputStream(savePath + "\\"
							+ filename);
					System.out.println(savePath + "\\"+ filename);
					// ����һ��������
					byte buffer[] = new byte[1024];
					// �ж��������е������Ƿ��Ѿ�����ı�ʶ
					int len = 0;
					// ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
					while ((len = in.read(buffer)) > 0) {
						// ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\"
						// + filename)����
						out.write(buffer, 0, len);
					}
					// �ر�������
					in.close();
					// �ر������
					out.close();
					// ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
					item.delete();
					message = "�ļ��ϴ��ɹ���";

				}
			}
		} catch (Exception e) {
			message = "�ļ��ϴ�ʧ�ܣ�";
			e.printStackTrace();

		}
		int authorID = 0;
		// ��ȡ��½�û���session,ͨ��session�е�bean��ȡ�û�ID
		LoginBean login = (LoginBean) request.getSession()
				.getAttribute("login");
		// ����е�½session ��ȡ���ߵ�ID
		if (login != null) {
			AuthorService authorService = new AuthorService();
			authorID = authorService.getAuthorID(login.getUsername());
		}
		if (authorID == 0) {
			System.out.println("����idΪ��");
			return;
		}
		AddNovelBean addNovel = new AddNovelBean();
		addNovel.setNovelName(novelName);
		addNovel.setPic(pic);
		addNovel.setCategory(category);
		addNovel.setContent(content);
		if (!addNovel.validate()) {
			request.setAttribute("addNovel", addNovel);
			request.getRequestDispatcher("/AuthorAddBook.jsp").forward(request,
					response);
			return;
		}
		// ���ý�������С˵��Ϣ
		NovelBean novelBean = new NovelBean();
		novelBean.setNovelName(novelName);
		novelBean.setPic(pic);
		novelBean.setContent(content);
		novelBean.setCategory(category);
		novelBean.setClickCount(0);
		novelBean.setProgress("δ���");
		novelBean.setWordCount(0);
		novelBean.setPrice(10);
		novelBean.setAuthorID(authorID);
		AuthorService service = new AuthorService();
		boolean isAddNovel = service.isAddNovel(novelBean);
		if (isAddNovel) {
			request.setAttribute("novelBean", novelBean);
			request.getRequestDispatcher("/AuthorAddBookSucess.jsp").forward(
					request, response);
		} else {
			request.getRequestDispatcher("/AuthorAddBook.jsp").forward(request,
					response);

		}
	}
}
