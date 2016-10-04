package bean;

import java.util.HashMap;
import java.util.Map;
/**
 * 封装读者注册出错信息bean
 * @author lenovo
 *
 */
public class ReaderRegisterBean {
	private Map<String, String> errors = new HashMap<String, String>();

	public boolean validate() {
		boolean flag = true;
		if (readerName == null || readerName.trim().equals("")) {
			errors.put("readerName", "请输入读者名");
			flag = false;
		}
		if (password == null || password.trim().equals("")) {
			errors.put("password", "请输入密码");
			flag = false;
		}
		if (password != null && !password.equals(password2)) {
			errors.put("password2", "两次密码不匹配");
			flag = false;
		}
		return flag;
	}

	public void setErrorsMsg(String err, String errMsg) {
		if ((err != null) && (errMsg != null))
			errors.put(err, errMsg);

	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	private String readerName;
	private String userName;
	private String password;
	private String password2;
	private String sex;
}
