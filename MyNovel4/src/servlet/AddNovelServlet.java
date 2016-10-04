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
		// form 使用enctype='multipart/form-data'，无法用 request.getParameter获取参数
		String novelName = "";// 获得小说名;
		String content = "";// 获得简介;
		String category = "";// 获得小说类别;
		String pic = "";// 小说图片
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		// String savePath =
		 //this.getServletContext().getRealPath("/WEB-INF/upload");
		String savePath = this.getServletContext().getRealPath("/")
				+"\\upload";
		System.out.println(savePath);
		File file = new File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "目录不存在，需要创建");
			// 创建目录
			file.mkdir();
		}
		// 消息提示
		String message = "";
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");

			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				return;
			}

			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合
			// 每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					System.out.println(name + "=" + value);
					// FORM使用加密方式传递，不能通过 request.getParameter获取参数，只能比较
					if (name.equals("novelName"))
						novelName = value;
					if (name.equals("content"))
						content = value;
					if (name.equals("category"))
						category = value;
					System.out.println("novelName:" + novelName);

				} else {// 如果fileitem中封装的是上传文件
						// 得到上传的文件名称，
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename
							.substring(filename.lastIndexOf("\\") + 1);
					pic = filename;// 获取小说图片名

					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(savePath + "\\"
							+ filename);
					System.out.println(savePath + "\\"+ filename);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
						// + filename)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					item.delete();
					message = "文件上传成功！";

				}
			}
		} catch (Exception e) {
			message = "文件上传失败！";
			e.printStackTrace();

		}
		int authorID = 0;
		// 获取登陆用户的session,通过session中的bean获取用户ID
		LoginBean login = (LoginBean) request.getSession()
				.getAttribute("login");
		// 如果有登陆session 获取作者的ID
		if (login != null) {
			AuthorService authorService = new AuthorService();
			authorID = authorService.getAuthorID(login.getUsername());
		}
		if (authorID == 0) {
			System.out.println("作者id为空");
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
		// 设置将发布的小说信息
		NovelBean novelBean = new NovelBean();
		novelBean.setNovelName(novelName);
		novelBean.setPic(pic);
		novelBean.setContent(content);
		novelBean.setCategory(category);
		novelBean.setClickCount(0);
		novelBean.setProgress("未完结");
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
