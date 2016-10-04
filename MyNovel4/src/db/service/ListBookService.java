package db.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BrowserNovelListBean;
import db.DBHelper;

/**
 * 书籍列表的数据库操作
 * 
 * @author lenovo
 * 
 */
public class ListBookService {
	/**
	 * 根据作者的id查询该作者在数据库中的小说信息
	 * 
	 * @param authorID
	 *            作者的id
	 * @return 小说信息
	 */
	public ResultSet listbook(int authorID) {
		String sql = "select novelID,novelName,category,progress,"
				+ "clickCount,wordCount from novel " + "where authorID='"
				+ authorID + "'";
		ResultSet resultSet = DBHelper.executeQuery(sql);
		return resultSet;
	}

	/**
	 * 读者浏览小说时，展现小说数据的数据库操作
	 * 
	 * @return 数据库中的小说列表
	 */
	public List<BrowserNovelListBean> getBrowserNovelList() {
		List<BrowserNovelListBean> novelList = new ArrayList<BrowserNovelListBean>();
		String sql = "select novelID,novelName,category,pic,clickCount"
				+ ",progress,price from novel";
		ResultSet rs = DBHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				BrowserNovelListBean bean = new BrowserNovelListBean();
				bean.setNovelID(rs.getInt(1));
				bean.setNovelName(rs.getString(2));
				bean.setCategory(rs.getString(3));
				bean.setPic("upload"+"\\"+rs.getString(4));		
				bean.setClickCount(rs.getInt(5));
				bean.setProgress(rs.getString(6));
				bean.setPrice(rs.getFloat(7));
				novelList.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return novelList;

	}
}
