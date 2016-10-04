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
		 tran.setInner("夕阳西下。 溪水潺潺,绕村而过,牧童骑牛,吹笛而行。 村寨,家家升炊烟,隐约有人声。 沿途,水田中,插秧的老人呵呵笑着,朝牧童吆喝着。 “好一派祥和的农家景象。” 村口桥边,几道身影缓缓而至,各个衣着单调,不过身材魁梧,气势不凡,为首之人看着眼前一幕,笑了起来。 “几位可是路过?” 插秧老人亦见到几人,招呼起来。 “路过?”为首之人口中说着,走到田边,“老头,你们这村里住着一位老秀才,是也不是?” “你是说邱夫子?”老人质朴,闻言就喜,“你们是来找夫子的?可是他的朋友?” “朋友?”为首之人摇了摇头,“是我们大王要找他!” “大王?”老人面色陡变,急退了两步,就见前方那人倏地一张嘴,嘴唇急速扩张,转眼就有一人高下,里面通红一片! 腥臭扑鼻! 老人未及大喊,那嘴就扑了过来。 嘎嘣! 脆响声中,为首那人微微弯腰,脑袋竟成巨大狼头,狼嘴咀嚼,鲜血从嘴角流出。 “啊啊啊!” 凄厉尖叫从旁传来,却是牧童转头,看到了人首化狼、狼口吞人的一幕,惊骇而叫,他身下的水牛似也受了刺激,“哞哞”叫着,四蹄连踏,朝村子飞奔而去。 “跑的了么?” 狼头人吐出一截连筋带血的骨头,一挥手,身后几人嘿嘿狞笑,衣衫破碎,露出野兽模样,都朝村子扑去。 唰!唰!唰! 几个起落,已赶上了牧童。 惨叫、吼叫、吞咽,牧童连人带牛被分食,留下一地鲜血、碎骨,将泥土都给染红了。 而后,几人并不停留,入了村寨。 整个村子顿时沸腾,男人怒吼,女人哀嚎。 夕阳如火,云霞似烧,红透半边天。 狼头人的脑袋已缩为正常大小,嘴边残留有一抹鲜血,一步一步朝村走去。 行至一半,就有一头猪从村中跑出,肥头大脑、体态臃肿,但行进速度,呼哧呼哧的跑到狼头人身前。 “将军,”那猪脸露羞赧,沾满鲜血的嘴巴吧唧作响,“那穷措大被俺给弄死了。” “嗯?”狼头上的眼眉如人般皱起。 “不怪俺!不怪俺!”猪头摇晃起来,“俺还没碰那老东西,他就叽里咕噜的说了一堆,然后一头撞死了!” “撞死了?”狼头人脸露疑惑,但转念一想,“算了,一个老秀才,死就死了,这么大的年纪,咱公主八成也看不上,周围还有几个村子,读书人不会少,多抓几个。” 大猪闻言松了口气,接着又想到一事,连忙道:“那老秀才有个儿子,看上去一表人才,比那老秀才强多了!” “儿子?”狼头人点点头,“抓了,事后查一下,如无功名在身,就宰了吃肉,读书人沐浴圣人教化,那肉是极酥的。” 他的话音刚落,前面就传来几声急吼,紧接着,一名木脸汉子匆匆跑来:“将军!不好了!那秀才的儿子跑了!");
		 tran.setCharptName("第一章 三根毫毛");
		 json = mapper.writeValueAsString(tran);//简历一个json对象用于存novelName的值
		 out.write(json);
		out.flush();
		out.close();
	}

}
