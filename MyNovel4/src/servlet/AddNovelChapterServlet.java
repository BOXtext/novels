package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.service.NovelService;
import bean.AddNovelChapterbean;
import bean.ChapterBean;

public class AddNovelChapterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chapterName = request.getParameter("chapterName");
		String chapterContent = request.getParameter("chapterContent");
		Integer NovelID = (Integer) request.getSession().getAttribute(
				"mNovelID");
		int mNovelID = NovelID.intValue();
		System.out.println(mNovelID);
		AddNovelChapterbean addChapterbean = new AddNovelChapterbean();
		addChapterbean.setChapterName(chapterName);
		addChapterbean.setChapterContent(chapterContent);
		if (!addChapterbean.validate()) {
			request.setAttribute("addChapterbean", addChapterbean);
			request.getRequestDispatcher("/AuthorAddBookChapter.jsp").forward(
					request, response);
			return;
		}
		ChapterBean chapterBean = new ChapterBean();
		chapterBean.setNovelID(mNovelID);
		chapterBean.setChapterName(chapterName);
		chapterBean.setChapterContent(chapterContent);
		NovelService novelService = new NovelService();
		boolean isAddNovelChapter = novelService.isAddNovelChapter(chapterBean);
		if (!isAddNovelChapter) {
			request.getRequestDispatcher("/AAuthorAddBookChapter.jsp").forward(
					request, response);
			return;
		} else {
			int novelWord = novelService.getNovelWord(mNovelID);
			String wordCount = String.valueOf(novelWord
					+ chapterContent.length());
			novelService.updateNovelMsg("wordCount", wordCount, mNovelID);
			request.getSession().setAttribute("chapterBean", chapterBean);
			System.out.println("发表章节成功");
			response.setHeader("refresh",
					"0;url=AuthorAddBookChapterSucess.jsp");
		}
	}

}
