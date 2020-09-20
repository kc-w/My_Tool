package Bean.sp;


//商品模型
public class goods {
	private int Pid;
	private int Mid;
	private String Pname;
	private float Pprice;
	private int Pstock;
	private String Ptime;
	
	public goods(){
		
	}


	public int getMid() {
		return Mid;
	}

	public void setMid(int mid) {
		Mid = mid;
	}
	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		Pid = pid;
	}

	public String getPname() {
		return Pname;
	}

	public void setPname(String pname) {
		Pname = pname;
	}

	public float getPprice() {
		return Pprice;
	}

	public void setPprice(float pprice) {
		Pprice = pprice;
	}

	public int getPstock() {
		return Pstock;
	}

	public void setPstock(int pstock) {
		Pstock =pstock;
	}

	public String getPtime() {
		return Ptime;
	}

	public void setPtime(String ptime) {
		Ptime = ptime;
	}



}
