package 设计模式实验.策略模式;

public class BookStore {
	private BookCost bc;
	public void setBookCost(BookCost bc) {
		this.bc = bc;
	}
	public void cost() {
		bc.cost();
	}

}
