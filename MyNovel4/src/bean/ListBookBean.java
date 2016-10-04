package bean;
/**
 *封装图书列表的信息bean 
 * @author lenovo
 *
 */
public class ListBookBean {
	private String novelName;
	private String category;
	private String progress;
	private int clickCount;
	private int wordCount;
	private int novelID;
	private int xuhao;

	public int getXuhao() {
		return xuhao;
	}

	public void setXuhao(int xuhao) {
		this.xuhao = xuhao;
	}

	public int getNovelID() {
		return novelID;
	}

	public void setNovelID(int novelID) {
		this.novelID = novelID;
	}

	public ListBookBean(int xuhao,int novelID, String novelName, String category,
			String progress, int clickCount, int wordCount) {
		super();
		this.xuhao = xuhao;
		this.novelID = novelID;
		this.novelName = novelName;
		this.category = category;
		this.progress = progress;
		this.clickCount = clickCount;
		this.wordCount = wordCount;
	}

	public String getNovelName() {
		return novelName;
	}

	public void setNovelName(String novelName) {
		this.novelName = novelName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

}
