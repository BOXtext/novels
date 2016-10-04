package bean;
/**
 * 封装章节信息bean
 * @author lenovo
 *
 */
public class ChapterBean {
	private int chapterID;
	private String chapterName;
	private String chapterContent;
	private int novelID;
	public int getChapterID() {
		return chapterID;
	}
	public void setChapterID(int chapterID) {
		this.chapterID = chapterID;
	}
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
	public int getNovelID() {
		return novelID;
	}
	public void setNovelID(int novelID) {
		this.novelID = novelID;
	}
	
}	
