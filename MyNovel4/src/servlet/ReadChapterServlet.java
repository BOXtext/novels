package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import db.service.NovelService;
import bean.ChapterBean;

public class ReadChapterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		String chapterID = request.getParameter("chapterID");
		int id=0;
		 if(chapterID!=null){
			 id = Integer.parseInt(chapterID);
			  System.out.println("执行了parseInt");
			  System.out.println(id);//小说的数据库id
		 }
		NovelService service = new NovelService();
		String chapterName = service.getChapterName(id);
		PrintWriter out = response.getWriter();
		 
		@SuppressWarnings("unchecked")
		List<ChapterBean> list = (List<ChapterBean>) request.getSession()
				.getAttribute("chapterList");
		String chapterContent = "";
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getChapterID() == id) {
					chapterContent = list.get(i).getChapterContent();
					System.out.println();
				}
			}
		}	
		request.getSession().setAttribute("chapterContent", chapterContent);
		request.getSession().setAttribute("chapterName", chapterName);
		request.getRequestDispatcher("/ReadChapter.jsp").forward(request,
				response);
	}

}
