package 设计模式实验.外观模式;

public class Mainframe {
	private Memory m;
	private Cpu c;
	private HardDisk d;
	private OS o;
	
	public Mainframe() {
		m = new Memory();
		c = new Cpu();
		d = new HardDisk();
		o = new OS();		
	}
	
	public void on() {
		m.check();
		c.run();
		d.read();
		o.load();
		System.out.println("开机成功...");
	}
	
	public void off1() {
		System.out.print("如果");
		m.off();
		System.out.println("开机失败...");
	}
	
	public void off2() {
		System.out.print("如果");
		c.off();
		System.out.println("开机失败...");
	}
	
	public void off3() {
		System.out.print("如果");
		d.off();
		System.out.println("开机失败...");
	}
	
	public void off4() {
		System.out.print("如果");
		o.off();
		System.out.println("开机失败...");
	}
}
