package 设计模式实验.策略模式;

public class NovelBook implements BookCost {
	public void cost() {
		System.out.println("小说类图书每100元有10元的折扣...");
	}
}
