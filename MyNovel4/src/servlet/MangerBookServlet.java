package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.service.AuthorService;
import db.service.ListBookService;
import db.service.NovelService;
import bean.ListBookBean;
import bean.LoginBean;

public class MangerBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("小说servlet");
		int xuhao = 1;
		List<ListBookBean> books = new ArrayList<ListBookBean>();
		ListBookService service = new ListBookService();
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
		ResultSet result = service.listbook(authorID);

		try {
			while (result.next()) {
				int novelID = result.getInt(1);
				// NovelService novelService = new NovelService();
				// int novelWord = novelService.getNovelWord(novelID);
				// String wordCount = String.valueOf(novelWord);
				// novelService.updateNovelMsg("wordCount", wordCount, novelID);
				String novelName = result.getString(2);
				String category = result.getString(3);
				String progress = result.getString(4);
				int clickCount = result.getInt(5);
				int mWordCount = result.getInt(6);
				books.add(new ListBookBean(xuhao, novelID, novelName, category,
						progress, clickCount, mWordCount));
				xuhao++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("books", books);
		response.setHeader("refresh", "0;url=AuthorEditBook.jsp");
	}

}
