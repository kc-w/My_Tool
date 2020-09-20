package 设计模式实验.观察者模式;
/**
 * 观察者模式
 * @author 小石头
 *
 */
public class Test {
	public static void main(String args[]) {
		OnlineWare ow = new Subject();
		OnlineSystem os1 = new Guming(1);
		OnlineSystem os2 = new Guming(2);
		ow.add(os1);
		ow.add(os2);
		ow.notified();
		
	}

}
