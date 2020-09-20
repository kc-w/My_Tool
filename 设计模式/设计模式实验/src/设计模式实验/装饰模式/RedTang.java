package 设计模式实验.装饰模式;

public class RedTang extends Push {
	public RedTang(Drink d) {
		super(d);
		
	}
	public void cost() {
		super.cost();
		push();
	}
	public void push() {
		System.out.println("加点红糖...");
		System.out.println("+红糖费c元");
	}
}
