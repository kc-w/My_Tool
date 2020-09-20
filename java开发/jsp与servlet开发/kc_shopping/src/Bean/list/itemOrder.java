package Bean.list;

import Bean.sp.goods;
//订单模型
public class itemOrder {
	private int Oid;
	private int Cid;
	private int Mid;
	private String Olist;

	private String Otime;
	private goods good;
	private int number;
	
	public itemOrder(){
		
	}

	public String getOlist() {
		return Olist;
	}

	public void setOlist(String olist) {
		Olist = olist;
	}
	
	public int getOid() {
		return Oid;
	}

	public void setOid(int oid) {
		Oid = oid;
	}

	public int getCid() {
		return Cid;
	}

	public void setCid(int cid) {
		Cid = cid;
	}

	public int getMid() {
		return Mid;
	}

	public void setMid(int mid) {
		Mid = mid;
	}

	public String getOtime() {
		return Otime;
	}

	public void setOtime(String otime) {
		Otime = otime;
	}

	public goods getGood() {
		return good;
	}

	public void setGood(goods good) {
		this.good = good;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	//获得所买商品的单价
	public float getPrice(){
		return getGood().getPprice();
	}
	
	//获得所买商品的总价
	public float getTotalPrice(){
		return getNumber()*getPrice();
	}
	
	//取消商品订单
	public void cancelOrder(){
		setNumber(0);
	}
	
	//商品增长方式
	public void incrementNumber(){
		setNumber(getNumber()+1);
	}


}

