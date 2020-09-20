package asynchronous_error_thread;

public class main_class {
//异步:多个线程对某个方法或空间进行并发式的读写
//同步:多个线程对某个方法或空间进行排队的方式进行操作
	public static void main(String[] args) {
		gong_xiang_class sharedMemory=new gong_xiang_class();
		
		add_class t1=new add_class(sharedMemory);
		substr_class t2=new substr_class(sharedMemory);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sharedMemory.getNumber());

	}

}
