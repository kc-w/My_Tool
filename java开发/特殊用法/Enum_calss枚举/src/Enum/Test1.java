package Enum;
//用来提供常量,限定常量取值范围
//枚举类默认是final类型,不能被继承,也不能调用父类的构造器
public enum Test1 {
	CHUN(12,1,2),XIA(3,4,5),QIU(6,7,8),DONG(9,10,11);
	private int firstmonth;
	private int secondmonth;
	private int thirdmoth;
	//构造方法只能是private
	private Test1(int firstmonth,int secondmonth,int thirdmoth) {
		this.firstmonth=firstmonth;
		this.secondmonth=secondmonth;
		this.thirdmoth=thirdmoth;
	}
	public int getFirstmonth() {
		return firstmonth;
	}
	public void setFirstmonth(int firstmonth) {
		this.firstmonth = firstmonth;
	}
	public int getSecondmonth() {
		return secondmonth;
	}
	public void setSecondmonth(int secondmonth) {
		this.secondmonth = secondmonth;
	}
	public int getThirdmoth() {
		return thirdmoth;
	}
	public void setThirdmoth(int thirdmoth) {
		this.thirdmoth = thirdmoth;
	}
	
}
