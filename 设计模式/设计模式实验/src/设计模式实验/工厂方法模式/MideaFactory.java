package 设计模式实验.工厂方法模式;

public class MideaFactory implements Factory {
	public AirCondition produce() {
		System.out.println("美的工厂生产美的空调");
		return new MideaAirCondition();
	}
}
