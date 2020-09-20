package 设计模式实验.装饰模式;
/**
 * 装饰模式
 * @author 小石头
 *
 */
public class Test {
	public static void main(String[] args) {
		Drink d = new Tea();
		Milk m = new Milk(d);
		RedTang r = new RedTang(m);
		r.cost();
		
		Drink d1 = new Coffee();
		BingTang b1 = new BingTang(d1);
		b1.cost();
	}

}
