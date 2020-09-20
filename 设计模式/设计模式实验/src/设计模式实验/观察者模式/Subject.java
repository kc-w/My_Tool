package 设计模式实验.观察者模式;

public class Subject extends OnlineWare {
	public void notified() {
		System.out.println("股票价格变化幅度达到5%,新价格为xx元...");
		for(Object o:onlineSystem) {
			((OnlineSystem)o).update();	
		}
	}

}
