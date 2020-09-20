package Callable_FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//从一个线程中获取其他线程的返回结果
public class test1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> task1=new FutureTask<Integer>(new childThread1());
		Thread t1=new Thread(task1);
		t1.start();
		//获得子线程的计算结果
		System.out.println(task1.get());
	}

}
class childThread1 implements Callable<Integer>{

	public Integer call() throws Exception {
		int ret=0;
		
		for(int i=0;i<100;i++) {
			ret++;
		}
		return ret;
	}
	
}
