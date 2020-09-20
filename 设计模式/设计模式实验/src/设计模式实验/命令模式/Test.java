package 设计模式实验.命令模式;
/**
 * 命令模式
 * @author 小石头
 *
 */
public class Test {
	public static void main(String args[]) {
		MenuItem openCommand = new OpenCommand();
		MenuItem createCommand = new CreateCommand();
		MenuItem editCommand = new EditCommand();
		
		Menu m = new Menu(openCommand,createCommand,editCommand);
		
		m.open();
		m.create();
		m.edit();
		m.addMenuItem();
		
	}

}
