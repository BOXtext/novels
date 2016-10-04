package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ChapterBean;
import db.service.NovelService; 
import com.fasterxml.jackson.databind.ObjectMapper;
public class ReadNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String novelID = request.getParameter("novelID");
		ObjectMapper mapper = new ObjectMapper();  
		
		PrintWriter out = response.getWriter();
		// 根据小说id得到章节列表
		String json = null;//
		int mNovelID = 0;
		 trans tran = new trans();  
		 if(novelID!=null){
			  mNovelID = Integer.parseInt(novelID);
			  System.out.println("执行了parseInt");
			  System.out.println(mNovelID);//小说的数据库id
		 }
		
		NovelService service = new NovelService();
		List<ChapterBean> chapterList = service.getChapter(mNovelID);
		ResultSet rs = service.getNovelMsg("novelName", mNovelID);
		ResultSet r2 = service.getNovelMsg("clickCount", mNovelID);
		String novelName = "";
		int clickCount = 0;
		try {
			if (rs.next()) {
				novelName = rs.getString(1);
				tran.setName(chapterList);
				
			}
			if (r2.next()) {
				clickCount = r2.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(chapterList.toString());
		json = mapper.writeValueAsString(tran);//简历一个json对象用于存novelName的值
		String mClickCount = String.valueOf(clickCount + 1);
		service.updateNovelMsg("clickCount", mClickCount, mNovelID);
		request.getSession().setAttribute("novelName", novelName);
		request.getSession().setAttribute("chapterList", chapterList);
		request.getRequestDispatcher("/ReadNovel.jsp").forward(
				request, response);
		out.write(json);
		System.out.println(json);
		out.flush();
		 out.close();

		
	}

}
