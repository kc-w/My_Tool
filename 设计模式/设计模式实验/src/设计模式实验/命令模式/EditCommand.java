package 设计模式实验.命令模式;

public class EditCommand implements MenuItem {
	private BoardScreen bs;
	public EditCommand() {
		bs = new BoardScreen();
	}
	public void click() {
		bs.edit();
	}

}
