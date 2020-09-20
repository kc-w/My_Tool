package Tread;
//阻塞线程
public class Join_Tread {
	public static void main(String[] args) {
		
		AnothorThre own=new AnothorThre();
		Thread at=new Thread(own);
		at.start();
		
		MyThre mt=new MyThre(); 
		mt.start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		own.setBln(false);
		mt.setBln(false);
		
		try {
			//阻塞主线程，等待1,2线程完全执行结束,主线程才会继续执行
			at.join();
			mt.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程结束");

	}

}
//子线程类1
class AnothorThre implements Runnable{
	
	private boolean bln=true;
	
	public void setBln(boolean bln) {
		this.bln=bln;
	}
	
	public void run() {
		MyThre mt=new MyThre(); 
		while(bln) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println("1线程被调用一次");
		}
	}
	
}
//子线程类2
class MyThre extends Thread{
	
	private boolean bln=true;

	public void setBln(boolean bln) {
		this.bln=bln;
	}
	
	public void run() {
		while(bln) {
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println("2线程被调用一次");
		}
	}

}