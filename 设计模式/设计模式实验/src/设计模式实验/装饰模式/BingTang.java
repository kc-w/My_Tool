package 设计模式实验.装饰模式;

public class BingTang extends Push {
	public BingTang(Drink d) {
		super(d);
	}
	public void cost() {
		super.cost();
		push();
	}
	public void push() {
		System.out.println("加点冰糖...");
		System.out.println("+冰糖费d元");
	}
}
