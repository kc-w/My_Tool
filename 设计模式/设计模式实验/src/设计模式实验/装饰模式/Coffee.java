package 设计模式实验.装饰模式;

public class Coffee implements Drink {
	public Coffee() {
		System.out.println("来一杯咖啡...");
	}
	
	public void cost() {
		System.out.println("咖啡费b元");
	}

}
