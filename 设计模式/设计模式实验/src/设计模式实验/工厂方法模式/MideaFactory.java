package ���ģʽʵ��.��������ģʽ;

public class MideaFactory implements Factory {
	public AirCondition produce() {
		System.out.println("���Ĺ����������Ŀյ�");
		return new MideaAirCondition();
	}
}
