package db.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BrowserNovelListBean;
import db.DBHelper;

/**
 * �鼮�б�����ݿ����
 * 
 * @author lenovo
 * 
 */
public class ListBookService {
	/**
	 * �������ߵ�id��ѯ�����������ݿ��е�С˵��Ϣ
	 * 
	 * @param authorID
	 *            ���ߵ�id
	 * @return С˵��Ϣ
	 */
	public ResultSet listbook(int authorID) {
		String sql = "select novelID,novelName,category,progress,"
				+ "clickCount,wordCount from novel " + "where authorID='"
				+ authorID + "'";
		ResultSet resultSet = DBHelper.executeQuery(sql);
		return resultSet;
	}

	/**
	 * �������С˵ʱ��չ��С˵���ݵ����ݿ����
	 * 
	 * @return ���ݿ��е�С˵�б�
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
