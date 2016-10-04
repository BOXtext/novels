package db.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.ChapterBean;
import bean.NovelBean;
import bean.ReaderOrder;
import db.DBHelper;

public class NovelService {

	// 读者查询获得图书列表信息
	public List<NovelBean> QueryNovel(String novelName) {
		List<NovelBean> novelList = new ArrayList<NovelBean>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from novel");

		if (!novelName.equals(""))// 如果书名不为空，加入WHER条件
			sb.append(String.format(" where left(novelName,%d)='%s'",
					novelName.length(), novelName));

		//System.out.println(sb.toString());
		ResultSet rs = DBHelper.executeQuery(sb.toString());
		try {
			while (rs.next()) {
				NovelBean bean = new NovelBean();
				bean.setNovelID(rs.getInt(1));
				bean.setNovelName(rs.getString(2));
				bean.setPic(rs.getString(3));
				bean.setContent(rs.getString(4));
				bean.setCategory(rs.getString(5));
				bean.setClickCount(rs.getInt(6));
				bean.setProgress(rs.getString(7));
				bean.setClickCount(rs.getInt(8));
				bean.setPrice(rs.getFloat(9));
				novelList.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return novelList;

	}

	// 读者根据小说ID，获得一本小说信息
	public NovelBean GetNovel(String novelID) {
		NovelBean bean = new NovelBean();
		// 构造根据小说ID获取小说的查询语句
		String sql = " select * from novel where NovelID='" + novelID + "'";
		ResultSet rs = DBHelper.executeQuery(sql);
		try {
			while (rs.next()) {

				bean.setNovelID(rs.getInt(1));
				bean.setNovelName(rs.getString(2));
				bean.setPic(rs.getString(3));
				bean.setContent(rs.getString(4));
				bean.setCategory(rs.getString(5));
				bean.setClickCount(rs.getInt(6));
				bean.setProgress(rs.getString(7));
				bean.setWordCount(rs.getInt(8));
				bean.setPrice(rs.getFloat(9));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean; // 返回一本小说对象

	}

	// 读者生成订单
	public boolean CreateOrder(List<NovelBean> novelList, String readerID) {
		float orderTotalPrice = 0;// 计算总价
		for (NovelBean n : novelList)
			orderTotalPrice += n.getPrice();

		Date date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df = DateFormat.getDateTimeInstance();// 可以精确到时分秒
		String orderCode = df.format(date) + readerID;// 生成订单代码 时间+读者编号,表示唯一性

		ReaderOrder order = new ReaderOrder();
		order.setOrderCode(orderCode);
		order.setOrderDatetime(timeFormat.format(date));
		order.setOrderTotalPrice(orderTotalPrice);
		order.setReaderID(Integer.parseInt(readerID));

		StringBuffer sb = new StringBuffer();
		sb.append("insert into readerOrder (OrderCode,ReaderID,OrderDatetime,OrderTotalPrice) ");
		String temp = String.format(" values('%s',%s,'%s',%f)",
				order.getOrderCode(), order.getReaderID(),
				order.getOrderDatetime(), order.getOrderTotalPrice());
		sb.append(temp);
		System.out.println(sb.toString());
		int rows = DBHelper.executeNonQuery(sb.toString());// 插入订单
		if (rows == 0)
			return false;

		// 插入订单细节
		for (NovelBean n : novelList) {
			String sql = String
					.format("insert into readerOrderDetail(OrderCode,NovelID) values('%s',%d)",
							orderCode, n.getNovelID());
			System.out.println(sql);
			rows = DBHelper.executeNonQuery(sql);// 插入订单
			if (rows == 0)
				return false;
		}
		return true;
	}

	/**
	 * 发表章节内容 并插入数据库
	 * 
	 * @param chapterBean
	 *            封装章节的信息bean
	 * @return 是否发表章节成功
	 */
	public boolean isAddNovelChapter(ChapterBean chapterBean) {
		if (null == chapterBean) {
			return false;
		}
		String sql = String.format(
				"insert into chapter(chapterName,chapterContent,novelID)"
						+ "values('%s','%s','%s')",
				chapterBean.getChapterName(), chapterBean.getChapterContent(),
				chapterBean.getNovelID());
		int result = DBHelper.executeNonQuery(sql);
		if (0 == result) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 更新小说表的信息
	 * 
	 * @param msg
	 *            要更新的小说的字段
	 * @param updateMsg
	 *            字段更新值
	 * @param novelID
	 *            要更新的小说的id
	 */
	public void updateNovelMsg(String msg, String updateMsg, int novelID) {
		String sql = "update novel set " + msg + "='" + updateMsg
				+ "' where novelID=" + novelID;
		DBHelper.executeNonQuery(sql);
	}

	public ResultSet getNovelMsg(String s, int novelID) {
		String sql = "select " + s + " from novel where novelID='" + novelID
				+ "'";
		return DBHelper.executeQuery(sql);
	}

	/**
	 * 得到小说的总字数
	 * 
	 * @param mNovelID
	 *            小说的编号
	 * @return 小说的此时总字数
	 */
	public int getNovelWord(int mNovelID) {
		String sql = "select chapterContent from chapter where novelID='"
				+ mNovelID + "'";
		ResultSet result = DBHelper.executeQuery(sql);
		String s = "";
		int len = 0;
		try {
			while (result.next()) {
				s = result.getString(1);
				len = len + s.length();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return len;
	}

	/**
	 * 章节列表
	 * 
	 * @param novelID
	 *            小说的标号
	 * @return 数据库中所有的章节形成的列表
	 */
	public List<ChapterBean> getChapter(int novelID) {
		List<ChapterBean> list = new ArrayList<ChapterBean>();
		String sql = "select chapterID,chapterName,chapterContent from chapter where novelID='"
				+ novelID + "'";
		//System.out.println(sql);
		ResultSet rs = DBHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				ChapterBean b = new ChapterBean();
				b.setChapterID(rs.getInt(1));
				b.setChapterName(rs.getString(2));
				b.setChapterContent(rs.getString(3));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public String getChapterName(int chapterID) {
		String chapterName = "";
		String sql = "select chapterName from chapter where chapterID='"
				+ chapterID + "'";
		ResultSet rs = DBHelper.executeQuery(sql);
		try {
			if (rs.next()) {
				chapterName = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chapterName;
	}
	/**
	 * 章节列表
	 * 
	 * @param novelID
	 *            小说的标号
	 * @return 数据库中所有的章节形成的列表
	 */
	public List<NovelBean> getNovelName(int novelID) {
		List<NovelBean> list = new ArrayList<NovelBean>();
		String sql = "select NovelName from novel where novelID='"
				+ novelID + "'";
		//System.out.println(sql);
		ResultSet rs = DBHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				NovelBean b = new NovelBean();
				b.setNovelName(rs.getString(2));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
