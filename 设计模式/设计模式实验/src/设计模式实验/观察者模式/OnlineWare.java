package ���ģʽʵ��.�۲���ģʽ;

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
