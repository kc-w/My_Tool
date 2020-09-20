package asynchronous_error_thread;
//加操作
public class add_class extends Thread{
	
	private gong_xiang_class sharedMemory=null;
	
	public add_class(gong_xiang_class sharedMemory) {
		this.sharedMemory=sharedMemory;
	}

	public void run() {
		for(int j=0;j<10;j++) {
			for(int i=0;i<50;i++) {
				sharedMemory.add();
				
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						return;
					}
			}
		}
	}

	

}
