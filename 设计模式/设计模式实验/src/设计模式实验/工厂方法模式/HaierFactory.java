package ���ģʽʵ��.��������ģʽ;

public class HaierFactory implements Factory {
	public AirCondition produce() {
		System.out.println("�����������������յ�");
		return new HaierAirCondition();
	}

}
