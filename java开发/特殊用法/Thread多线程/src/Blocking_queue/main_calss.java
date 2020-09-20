package Blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

//阻塞队列
public class main_calss {

	public static void main(String[] args) {
		//创建阻塞队列
		ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<String>(3);
		
		queue_object bj=new queue_object(queue,"北京");
		queue_object sz=new queue_object(queue,"深圳");
		queue_object sh=new queue_object(queue,"上海");
		
		yun y=new yun(queue);
		
		bj.start();
		sz.start();
		sh.start();
		y.start();
		
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bj.Over();
		sz.Over();
		sh.Over();
		y.Over();

	}

}
