package 设计模式实验.抽象工厂模式;

public class PcFactory implements Factory {
	public Cpu produceCpu() {
		System.out.println("Pc工厂将生产一个PcCpu...");
		return new PcCpu();
	}
	public Ram produceRam() {
		System.out.println("Pc工厂将生产一个PcRam...");
		return new PcRam();
	}

}
