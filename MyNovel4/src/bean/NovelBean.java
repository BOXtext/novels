package bean;
/**
 * 封装小说信息bean
 * @author lenovo
 *
 */
public class NovelBean {
	public int getNovelID() {
		return NovelID;
	}

	public void setNovelID(int novelID) {
		NovelID = novelID;
	}

	public String getNovelName() {
		return NovelName;
	}

	public void setNovelName(String novelName) {
		NovelName = novelName;
	}

	public String getPic() {
		return Pic;
	}

	public void setPic(String pic) {
		Pic = pic;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getProgress() {
		return Progress;
	}

	public void setProgress(String progress) {
		Progress = progress;
	}

	public int getClickCount() {
		return ClickCount;
	}

	public void setClickCount(int clickCount) {
		ClickCount = clickCount;
	}

	public int getWordCount() {
		return WordCount;
	}

	public void setWordCount(int wordCount) {
		WordCount = wordCount;
	}

	public int getAuthorID() {
		return AuthorID;
	}

	public void setAuthorID(int authorID) {
		AuthorID = authorID;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	private int NovelID;
	private String NovelName;
	private String Pic;
	private String Content;
	private String Category;
	private String Progress;
	private int ClickCount;
	private int WordCount;
	private int AuthorID;
	private float Price;
}
