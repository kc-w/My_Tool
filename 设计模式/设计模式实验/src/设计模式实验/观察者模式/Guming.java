package 设计模式实验.观察者模式;

public class Guming implements OnlineSystem {
	int i;
	public Guming (int i) {
		this.i = i;
	}
	public void update() {
		System.out.println("股民"+i+"收到最新价格通知...");
	}

}
