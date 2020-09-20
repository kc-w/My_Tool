package Condition_class;

public class reduce extends Thread {

	private warehouse wh=null;
	
	public reduce(warehouse wh) {
		this.wh=wh;
	}
    //将消费数传给仓库
	public void run() {
		for(int i=0;i<10;i++) {
			int needNumber=(int)(10*Math.random());
			
			try {
				wh.consume(needNumber);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
