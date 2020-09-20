package 设计模式实验.工厂方法模式;
/**
 * 工厂方法模式
 * @author 小石头
 *
 */
public class Test {
	public static void main(String []args) {
		Factory f = new HaierFactory();
		AirCondition ad = f.produce();
		ad.play();
		
		Factory f1 = new MideaFactory();
		AirCondition ad1 = f1.produce();
		ad1.play();
		
	}

}
