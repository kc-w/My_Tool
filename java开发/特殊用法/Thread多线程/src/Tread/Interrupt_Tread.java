package Tread;
//线程在休眠时进行中断
public class Interrupt_Tread {
		public static void main(String[] args) {
			
			AnothorThrea own=new AnothorThrea();
			Thread at=new Thread(own);
			at.start();
			
			MyThrea mt=new MyThrea(); 
			mt.start();
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			own.setBln(false);
			mt.setBln(false);
			//不会中断一个正在运行的线程,如果线程被wait,join和sleep三种方法之一阻塞，此时调用该线程的interrupt()方法，那么该线程将抛出一个 InterruptedException中断异常（该线程必须事先预备好处理此异常），从而提早地终结被阻塞状态
			mt.interrupt();

		}

	}
//子线程类1
class AnothorThrea implements Runnable{
		
		private boolean bln=true;
		
		public void setBln(boolean bln) {
			this.bln=bln;
		}
		
		public void run() {
			while(bln) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			System.out.println("子线程类1被调用一次");
			}
		}
		
	}
//子线程类2
class MyThrea extends Thread{
		
		private boolean bln=true;

		public void setBln(boolean bln) {
			this.bln=bln;
		}
		
		public void run() {
			/*
			 虽然主线程已经将bln设为false,但是2线程还处于休眠,while循环并不会执行退出,线程也就不会退出
			 ,在主线程内调用2线程的interrupt()方法后处于休眠的线程会立即引发InterruptedException异常
			 ,同时执行catch内的语句,强行中断休眠,执行return退出循环,结束2线程
			 */
			while(bln) {
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					System.out.println("捕捉到异常，子线程类2中断休眠执行退出");
					return;
				}
			System.out.println("子线程类2被调用一次");
			}
		}

}
