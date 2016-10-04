package db.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBHelper;
import bean.LoginBean;
import bean.ReaderBean;

/**
 * ��װ���ߵĸ������ݿ�ҵ�����
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
	 * �ж϶����Ƿ����ʸ�ע�ᣬ���������ݲ������ݿ�
	 * 
	 * @param readBean
	 *            ��װ���ߵ���Ϣbean
	 * @return �Ƿ�ע��ɹ�
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
				System.out.println("���ݿ���д���");
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
	 * �ж��Ƿ��ܹ���½
	 * 
	 * @param loginBean
	 *            ��װ�Ñ���½����Ϣbean
	 * @return true �ܵ�½ false ���ܵ�½
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
