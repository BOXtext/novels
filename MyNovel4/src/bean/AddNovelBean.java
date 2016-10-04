package bean;

import java.util.HashMap;
import java.util.Map;
/**
 * 封装小说出错信息bean
 * @author lenovo
 *
 */
public class AddNovelBean {
	private String novelName;
	private String pic;
	private String content;
	public String getNovelName() {
		return novelName;
	}
	public void setNovelName(String novelName) {
		this.novelName = novelName;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	private String category;
	private Map<String,String> errors=new HashMap<String,String>();
	public boolean validate()
	{
		boolean flag=true;
		 if(novelName==null || novelName.trim().equals("")){
		 errors.put("novelName", "请输入小说名");
		 flag=false;
		 }
		 if(content==null || content.trim().equals("")){
		 errors.put("content", "请输入小说简介");
		 flag=false;
		 }	 
		return flag;	
	}
	public void setErrorsMsg(String err,String errMsg) {
		if((err!=null)&&(errMsg!=null))
			errors.put(err,errMsg);
	}
	
	public Map<String,String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String,String> errors) {
		this.errors = errors;
	}
}
