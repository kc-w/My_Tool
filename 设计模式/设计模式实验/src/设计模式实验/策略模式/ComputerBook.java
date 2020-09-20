package 设计模式实验.策略模式;

public class ComputerBook implements BookCost {
	public void cost() {
		System.out.println("计算机类图书每本都有10%的折扣...");
	}

}
