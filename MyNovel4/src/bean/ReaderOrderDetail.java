package bean;
/**
 * 封装读者订单详情bean
 * @author lenovo
 *
 */
public class ReaderOrderDetail {
	private int readerOrderDetailID;
	private String orderCode;
	private int novelID;

	public int getReaderOrderDetailID() {
		return readerOrderDetailID;
	}

	public void setReaderOrderDetailID(int readerOrderDetailID) {
		this.readerOrderDetailID = readerOrderDetailID;
	}

	
	public int getNovelID() {
		return novelID;
	}

	public void setNovelID(int novelID) {
		this.novelID = novelID;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

}
