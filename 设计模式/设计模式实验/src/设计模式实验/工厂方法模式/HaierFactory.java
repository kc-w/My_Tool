package 设计模式实验.工厂方法模式;

public class HaierFactory implements Factory {
	public AirCondition produce() {
		System.out.println("海尔工厂生产海尔空调");
		return new HaierAirCondition();
	}

}
