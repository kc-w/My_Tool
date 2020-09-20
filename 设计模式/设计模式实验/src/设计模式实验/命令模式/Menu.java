package 设计模式实验.命令模式;

public class Menu {
	private MenuItem openCommand,createCommand,editCommand;
	
	public Menu(MenuItem openCommand,MenuItem createCommand,MenuItem editCommand) {
		this.createCommand = createCommand;
		this.editCommand = editCommand;
		this.openCommand = openCommand;
	}
	public void open() {
		openCommand.click();
	}
	public void create() {
		createCommand.click();
	}
	public void edit() {
		editCommand.click();
	}
	public void addMenuItem() {
		System.out.println("可以增加一个菜单项");
	}

}
