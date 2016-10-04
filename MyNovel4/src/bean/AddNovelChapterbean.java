package bean;

import java.util.HashMap;
import java.util.Map;

public class AddNovelChapterbean {
	private String chapterName;
	private String chapterContent;
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getChapterContent() {
		return chapterContent;
	}
	public void setChapterContent(String chapterContent) {
		this.chapterContent = chapterContent;
	}

	private Map<String,String> errors=new HashMap<String,String>();
	public boolean validate()
	{
		boolean flag=true;
		 if(chapterName==null || chapterName.trim().equals("")){
		 errors.put("chapterName", "章节名为空，请填写章节名");
		 flag=false;
		 }
		 if(chapterContent==null || chapterContent.trim().equals("")){
		 errors.put("chapterContent", "章节内容为空，请填写相关内容");
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
