package Tread;
//控制子线程
public class Control_Tread {

	public static void main(String[] args) throws InterruptedException {
		child_Tread th=new child_Tread();
		th.start();//可执行状态runable
		
		//主线程休眠3秒
		Thread.sleep(3000);
		
		//改变标识符
		th.blocked();//等待状态wait
		
		//主线程休眠3秒
		Thread.sleep(3000);
		
		th.wakeup();
		
		//主线程休眠3秒
		Thread.sleep(3000);
		
		th.exit();//退出
	}

}
class child_Tread extends Thread{
	private boolean tf=true;
	private boolean wp=false;
	//创建synchronized关键字对象
	private Object obj=new Object();
	public void blocked() {
		wp=true;
		//obj.wait();如果wait()方法写在这相当于主线程调用wait()方法而不是子线程
	}
	public void wakeup() {
		wp=false;
		System.out.println("中断休眠，唤醒所有阻塞在synchronized的线程");
		synchronized(obj) {
		obj.notifyAll();
		}
	}
	public void exit() {
		this.tf=false;
		System.out.println("interrupt()引发异常，捕获异常后退出");
		this.interrupt();
	}
	public void run() {
		while(tf) {
			if(wp) {
				System.out.println("子线程进入休眠阻塞状态");
				try {
					//notifyAll和wait语句一定要编写在synchronized(){}块中
					synchronized(obj) {
						obj.wait();//子线程调用wait()
					}
				} catch (InterruptedException e) {
					return;
				}
		    }
			
			try {
				//每休眠一秒进行打印操作
				Thread.sleep(1000);
				System.out.println(666);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
}