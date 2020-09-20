package asynchronous_error_thread;

import java.util.concurrent.locks.ReentrantLock;
//对加减操作进行加锁,使得在同一时刻只能有一个线程进行操作
//该类同时被两个线程共享
public class gong_xiang_class {

	private int number=0;
	private static ReentrantLock lock=new ReentrantLock();

	public int getNumber() {
		return number;
	}
	
//	public void add() {
//		lock.lock();
//		number++;
//		lock.unlock();
//	}
//	public void substr() {
//		lock.lock();
//		number--;
//		lock.unlock();
//	}

    //synchronized关键字是一个简单锁,当不需要用到Lock这种复杂的锁时,可以使用它对线程进行锁定操作
	public synchronized void add() {
			number++;
	}
	public synchronized void substr() {
			number--;
	}

}

