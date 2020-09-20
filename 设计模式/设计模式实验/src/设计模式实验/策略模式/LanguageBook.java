package 设计模式实验.策略模式;

public class LanguageBook implements BookCost {
	public void cost() {
		System.out.println("语言类图书每本都有2元的折扣...");
	}

}
