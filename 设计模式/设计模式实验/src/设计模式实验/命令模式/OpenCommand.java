package 设计模式实验.命令模式;

public class OpenCommand implements MenuItem{
	private BoardScreen bs;
	public OpenCommand() {
		bs = new BoardScreen();
	}
	public void click() {
		bs.open();
	}

}
