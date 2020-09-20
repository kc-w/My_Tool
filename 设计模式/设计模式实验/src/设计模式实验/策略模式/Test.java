package 设计模式实验.策略模式;
/**
 * 策略模式
 * @author 小石头
 *
 */
public class Test {
	public static void main(String args[]) {
		BookCost cb = new ComputerBook();
		BookCost lb = new LanguageBook();
		BookCost nb = new NovelBook();
		BookStore b1 = new BookStore();
		b1.setBookCost(cb);
		b1.cost();
		b1.setBookCost(lb);
		b1.cost();
		b1.setBookCost(nb);
		b1.cost();

	}

}
