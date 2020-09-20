package Blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

public class yun extends Thread {
	
	private ArrayBlockingQueue<String> queue=null;
	private boolean over=true;

	public yun(ArrayBlockingQueue<String> queue) {
		this.queue=queue;
	}
	
	public void Over() {
		over=false;
		this.interrupt();
	}

	@Override
	public void run() {
		while(over) {
			try {
				//获取阻塞对列中的数据,并将其移除队列,如果没有数据则进行等待
				String yun=queue.take();
				//以"="为分割符,将分割后的字符串存入数组
				String[] shuzu=yun.split("=");
				
				String ret=null;
				if(Integer.valueOf(shuzu[1])>=8000&&Integer.valueOf(shuzu[1])<20000) {
					ret="小康";
				}else if(Integer.valueOf(shuzu[1])>=20000&&Integer.valueOf(shuzu[1])<50000) {
					ret="中产";
				}else if(Integer.valueOf(shuzu[1])>=50000) {
					ret="富裕";
				}else {
					ret="温饱";
				}
				
				System.out.println("工资状况-"+shuzu[0]+"的工资:"+shuzu[1]+"\t"+ret);
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				return;
			}
		}
	}
	
	

}
