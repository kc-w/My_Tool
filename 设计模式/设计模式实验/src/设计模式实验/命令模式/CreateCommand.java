package 设计模式实验.命令模式;

public class CreateCommand implements MenuItem {
	private BoardScreen bs;
	public CreateCommand() {
		bs = new BoardScreen();
	}
	public void click() {
		bs.create();
	}

}
