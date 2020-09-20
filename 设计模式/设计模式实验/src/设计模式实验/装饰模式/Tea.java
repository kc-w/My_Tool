package 设计模式实验.装饰模式;

public class Tea implements Drink {
	public Tea() {
		System.out.println("来一杯茶...");
	}
	
	public void cost() {
		System.out.println("茶费a元");
	}

}
