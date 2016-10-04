package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	static Connection con = null;
	static ResultSet rs = null;
	static Statement stmt = null;
//	static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MyNovel";
//	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/MyNovel?characterEncoding=utf8&mysqlEncoding=utf8;autoReconnect=true;autoReconnectForPools=true";
	static String user = "root"; // �û���
	static String password = "123456"; // ����

	public static void connectDB() {
		try {
			Class.forName(driver);
			System.out.println("���ݿ��������سɹ�");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (null == con) {
				con = DriverManager.getConnection(url, user, password);
				System.out.println("���ݿ����ӳɹ�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ִ��update ,delete,insert
	public static int executeNonQuery(String sql) {
		int result = 0;
		try {
			connectDB();
			stmt = con.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return result;
	}

	// ִ��select���
	public static ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			connectDB();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("select�ɹ�");

		} catch (SQLException err) {
			err.printStackTrace();
		}
		return rs;
	}
	public static void CloseRs() {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
		}
	}

	public static void CloseStm() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
		}
	}

	public static void CloseCon() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
		}
	}
	
}
