package 设计模式实验.观察者模式;

import java.util.ArrayList;

public abstract class OnlineWare {
	protected ArrayList onlineSystem = new ArrayList();
	public void add(OnlineSystem o) {
		onlineSystem.add(o);
	}
	public void remove(OnlineSystem o) { 
		onlineSystem.remove(o);
	}
	public abstract void notified();

}
