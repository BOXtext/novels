package bean;

import java.util.Date;
/**
 * 封装读者信息bean
 * @author lenovo
 *
 */
public class ReaderBean {
	public int getReaderID() {
		return ReaderID;
	}

	public void setReaderID(int readerID) {
		ReaderID = readerID;
	}

	public String getReaderName() {
		return ReaderName;
	}

	public void setReaderName(String readerName) {
		ReaderName = readerName;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getLastLoginTime() {
		return LastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	private int ReaderID;
	private String ReaderName;
	private String NickName;
	private String UserName;
	private String Password;
	private String Sex;
	private String Status;
	private Date LastLoginTime;
	private Date Birthday;
}
