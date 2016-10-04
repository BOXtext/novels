package bean;

import java.util.Date;
/**
 * 封装读者订单信息bean
 * @author lenovo
 *
 */
public class ReaderOrder {
	private int readerOrderID;
	private String orderCode;
	private int readerID;
	private String orderDatetime;
	private float orderTotalPrice;

	public int getReaderOrderID() {
		return readerOrderID;
	}

	public void setReaderOrderID(int readerOrderID) {
		this.readerOrderID = readerOrderID;
	}

	public int getReaderID() {
		return readerID;
	}

	public void setReaderID(int readerID) {
		this.readerID = readerID;
	}

	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public float getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(float orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

}
