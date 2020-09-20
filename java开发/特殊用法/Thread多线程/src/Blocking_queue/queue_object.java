package Blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

public class queue_object extends Thread {
	private ArrayBlockingQueue<String> queue=null;
	private String address=null;
	private boolean over=true;

	public queue_object(ArrayBlockingQueue<String> queue,String address) {
		this.queue=queue;
		this.address=address;
	}

	public void Over() {
		over=false;
		this.interrupt();
	}
	
	public void run() {
		while(over) {
			int yun=(int)(Math.random()*100000+3000);
			
			try {
				//把数据存入阻塞队列
				queue.put(this.address+"="+yun);
				Thread.sleep(100);
			}catch(InterruptedException e) {
				return;
			}
		}
	}
	

}
