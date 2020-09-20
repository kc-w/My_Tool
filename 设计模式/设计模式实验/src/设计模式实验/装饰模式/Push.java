package 设计模式实验.装饰模式;

public class Push implements Drink {
	private Drink d;
	public Push(Drink d) {
		this.d = d;
	}
	
	public void cost() {
		d.cost();
	}

}
