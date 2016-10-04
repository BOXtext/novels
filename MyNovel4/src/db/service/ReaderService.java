package db.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBHelper;
import bean.LoginBean;
import bean.ReaderBean;

/**
 * 封装读者的各种数据库业务操作
 * 
 * @author lenovo
 * 
 */
public class ReaderService {
	public String getReaderID(String readerName) {
		int readerID = 0;
		String sql = "select readerID from reader where username=\'"
				+ readerName + "\'";
		ResultSet result = DBHelper.executeQuery(sql);
		try {
			if (result.next()) {
				readerID = result.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = String.valueOf(readerID);
		return id;
	}

	/**
	 * 判断读者是否有资格注册，有则将其数据插入数据库
	 * 
	 * @param readBean
	 *            封装读者的信息bean
	 * @return 是否注册成功
	 */
	@SuppressWarnings("unused")
	public Boolean ReaderRegister(ReaderBean readBean) {
		int result;
		String sql = String
				.format("insert into reader(ReaderName,UserName,Password,Sex"
						+ ",Status)values('%s','%s','%s','%s','%s')",
						readBean.getReaderName(), readBean.getUserName(),
						readBean.getPassword(), readBean.getSex(),
						readBean.getStatus());
		if (readBean == null) {
			return false;
		}
		String username = readBean.getUserName();
		String select = "select userName from reader where userName=\'"
				+ username + "\'";
		//System.out.println(select);
		ResultSet resultSet = DBHelper.executeQuery(select);
		try {
			if (resultSet.next()) {
				System.out.println("数据库查有此人");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = DBHelper.executeNonQuery(sql);
		if (0 == result)
			return false;
		else
			return true;
	}

	/**
	 * 判断是否能够登陆
	 * 
	 * @param loginBean
	 *            封装用戶登陆的信息bean
	 * @return true 能登陆 false 不能登陆
	 */
	public boolean isLogin(LoginBean loginBean) {
		if (null == loginBean) {
			return false;
		}
		String sql = "select * from reader where username='"
				+ loginBean.getUsername() + "' and password='"
				+ loginBean.getPassword() + "'";
		//System.out.println(sql);
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

}
