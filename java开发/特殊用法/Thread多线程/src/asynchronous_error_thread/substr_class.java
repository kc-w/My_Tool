package asynchronous_error_thread;
//减操作
public class substr_class extends Thread{

private gong_xiang_class sharedMemory=null;
	
	public substr_class(gong_xiang_class sharedMemory) {
		this.sharedMemory=sharedMemory;
	}

	public void run() {
		for(int j=0;j<10;j++) {
			for(int i=0;i<50;i++) {
				sharedMemory.substr();
				
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						return;
					}
			}
		}
	}

}
