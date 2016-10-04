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

	// ���߲�ѯ���ͼ���б���Ϣ
	public List<NovelBean> QueryNovel(String novelName) {
		List<NovelBean> novelList = new ArrayList<NovelBean>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from novel");

		if (!novelName.equals(""))// ���������Ϊ�գ�����WHER����
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

	// ���߸���С˵ID�����һ��С˵��Ϣ
	public NovelBean GetNovel(String novelID) {
		NovelBean bean = new NovelBean();
		// �������С˵ID��ȡС˵�Ĳ�ѯ���
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
		return bean; // ����һ��С˵����

	}

	// �������ɶ���
	public boolean CreateOrder(List<NovelBean> novelList, String readerID) {
		float orderTotalPrice = 0;// �����ܼ�
		for (NovelBean n : novelList)
			orderTotalPrice += n.getPrice();

		Date date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df = DateFormat.getDateTimeInstance();// ���Ծ�ȷ��ʱ����
		String orderCode = df.format(date) + readerID;// ���ɶ������� ʱ��+���߱��,��ʾΨһ��

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
		int rows = DBHelper.executeNonQuery(sb.toString());// ���붩��
		if (rows == 0)
			return false;

		// ���붩��ϸ��
		for (NovelBean n : novelList) {
			String sql = String
					.format("insert into readerOrderDetail(OrderCode,NovelID) values('%s',%d)",
							orderCode, n.getNovelID());
			System.out.println(sql);
			rows = DBHelper.executeNonQuery(sql);// ���붩��
			if (rows == 0)
				return false;
		}
		return true;
	}

	/**
	 * �����½����� ���������ݿ�
	 * 
	 * @param chapterBean
	 *            ��װ�½ڵ���Ϣbean
	 * @return �Ƿ񷢱��½ڳɹ�
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
	 * ����С˵�����Ϣ
	 * 
	 * @param msg
	 *            Ҫ���µ�С˵���ֶ�
	 * @param updateMsg
	 *            �ֶθ���ֵ
	 * @param novelID
	 *            Ҫ���µ�С˵��id
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
	 * �õ�С˵��������
	 * 
	 * @param mNovelID
	 *            С˵�ı��
	 * @return С˵�Ĵ�ʱ������
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
	 * �½��б�
	 * 
	 * @param novelID
	 *            С˵�ı��
	 * @return ���ݿ������е��½��γɵ��б�
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
	 * �½��б�
	 * 
	 * @param novelID
	 *            С˵�ı��
	 * @return ���ݿ������е��½��γɵ��б�
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
