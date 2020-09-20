package 设计模式实验.抽象工厂模式;

public class MacFactory implements Factory {
	public Cpu produceCpu() {
		System.out.println("Mac工厂将生产一个MacCpu...");
		return new MacCpu();
	}
	public Ram produceRam() {
		System.out.println("Mac工厂将生产一个MacRam...");
		return new MacRam();
	}

}
