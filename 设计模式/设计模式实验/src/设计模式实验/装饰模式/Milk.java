package 设计模式实验.装饰模式;

public class Milk extends Push {
	public Milk(Drink d) {
		super(d);
	}
	public void cost() {
		super.cost();
		push();
	}
	public void push() {
		System.out.println("加点奶油...");
		System.out.println("+奶油费e元");
	}
}
