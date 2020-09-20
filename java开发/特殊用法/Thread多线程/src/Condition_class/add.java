package Condition_class;

public class add extends Thread {

	private warehouse wh=null;
	
	public add(warehouse wh) {
		this.wh=wh;
	}
    //将生产数传给仓库
	public void run() {
		for(int i=0;i<10;i++) {
			int addNumber=(int)(10*Math.random());
			
			try {
				wh.produce(addNumber);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
