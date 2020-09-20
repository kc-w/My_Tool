package Tread;
//线程的创建及休眠
public class Create_Tread {
   //main()方法是主线程的核心方法,主线程从该方法开始执行,方法执行完后主线程终止
	public static void main(String[] args) {
		
		//如果想要运行该线程的话需要创建子线程对象再把子线程对象放到Thread中
		AnothorThread own=new AnothorThread();
		Thread at=new Thread(own);
		//使线程处于可运行（就绪）状态,等待操作系统来分配cpu
		at.start();
		
		MyThread mt=new MyThread(); 
		//使线程处于可运行（就绪）状态,等待操作系统来分配cpu
		mt.start();
		
		try {
			//主线程休眠10秒
			System.out.println("主线程休眠10秒");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		own.setBln(false);
		System.out.println("主线程休眠结束，重新被唤醒，执行结束子线程1");
		//mt.setBln(false);

	}

}
//子线程类1:实现Runnable接口来创建子线程
class AnothorThread implements Runnable{
	
	private boolean bln=true;
	
	public void setBln(boolean bln) {
		this.bln=bln;
	}
	
	public void run() {
		while(bln) {
			try {
				//休眠1秒后该线程将处于可运行状态
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println("子线程类1被调用一次");
		}
	}
	
}
//子线程类2:继承Thread来实现子线程
class MyThread extends Thread{
	
	private boolean bln=true;

	public void setBln(boolean bln) {
		this.bln=bln;
	}
	
	//run()方法是子线程的核心方法,子线程从该方法开始执行,方法执行完后子线程终止
	public void run() {
		while(bln) {
			try {
				//休眠3秒后该线程将处于可运行状态
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println("子线程类2被调用一次");
		}
	}
}
