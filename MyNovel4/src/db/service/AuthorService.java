package db.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBHelper;
import bean.AuthorBean;
import bean.LoginBean;
import bean.NovelBean;

/**
 * ���b���ߵĸ������ݿ����ҵ��
 * 
 * @author lenovo
 * 
 */
public class AuthorService {

	public int getAuthorID(String authorName) {
		int authorID = 0;
		String sql = "select authorID from author where username=\'"
				+ authorName + "\'";
		ResultSet result = DBHelper.executeQuery(sql);
		try {
			if (result.next()) {
				authorID = result.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authorID;
	}

	/**
	 * �Д������Ƿ��]��
	 * 
	 * @param authorBean
	 *            ���b���ߵ���Ϣbean
	 * @return �Ƿ��܉��]��
	 */
	public Boolean Register(AuthorBean authorBean) {
		int result;
		String sql = String.format(
				"insert into author(AuthorName,UserName,Password,Sex,"
						+ "Status) " + "values('%s','%s','%s','%s','%s')",
				authorBean.getAuthorName(), authorBean.getUsername(),
				authorBean.getPassword(), authorBean.getSex(),
				authorBean.getStatus());
		if (authorBean == null) {
			return false;
		}
		String username = authorBean.getUsername();
		String select = "select userName from author where userName=\'"
				+ username + "\'";
		System.out.println(select);
		ResultSet resultSet = DBHelper.executeQuery(select);
		try {
			if (resultSet.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = DBHelper.executeNonQuery(sql);
		if (result == 0)
			return false;
		else
			return true;
	}

	/**
	 * �Д������Ƿ��܉���
	 * 
	 * @param loginBean
	 *            ���b���ߵ�ꑵ���Ϣbean
	 * @return �ܷ���
	 */
	public boolean isLogin(LoginBean loginBean) {
		if (null == loginBean) {
			return false;
		}
		String username = null;
		String password = null;
		String sql = "select username,password from author where username='"
				+ loginBean.getUsername() + "' and password='"
				+ loginBean.getPassword() + "'";
		try {
			ResultSet result = DBHelper.executeQuery(sql);
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * �Ƿ��ܹ�����С˵���������ݿ�
	 * 
	 * @param novleBean
	 *            ��װ��С˵��Ϣ
	 * @return �Ƿ񷢲�С˵
	 */
	public boolean isAddNovel(NovelBean novelBean) {
		if (null == novelBean) {
			return false;
		}
		String sql = String
				.format("insert into novel(NovelName,pic,Content,Category,"
						+ "ClickCount,Progress,WordCount,price,AuthorID)"
						+ "values('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
						novelBean.getNovelName(), novelBean.getPic(),
						novelBean.getContent(), novelBean.getCategory(),
						novelBean.getClickCount(), novelBean.getProgress(),
						novelBean.getWordCount(), novelBean.getPrice(),
						novelBean.getAuthorID());
		int result = DBHelper.executeNonQuery(sql);
		if (0 == result) {
			return false;
		} else {
			return true;
		}

	}
}
