package Callable_FutureTask;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//线程池可以提高多线程效率
public class test2 {

	public static void main(String[] args){
		//创建线程池执行器
		ExecutorService ec=Executors.newFixedThreadPool(10);
		
		Filefinder file=new Filefinder(new File("d:\\"),".exe");
		childThrea task=new childThrea();
		//将子线程放入线程池并进行执行
		Future<Integer> f=ec.submit(file);
		Future<Integer> t=ec.submit(task);
		
		try {
			//获得计算结果
			System.out.println(f.get());
			System.out.println(t.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}finally {
			//关闭线程池
			ec.shutdown();
		}

	}

}
class Filefinder implements Callable<Integer>{

	private File path=null;
	private String prefix=null;
	private int cont=0;
	
	public Filefinder(File path,String prefix) {
		this.path=path;
		this.prefix=prefix;
	}
	
	public Integer call() throws Exception {
		//判断文件夹是否存在
		if(!path.exists()) {
			return cont;
		}
		search(path);
		return cont;
	}
	private void search(File path) {
		System.out.println(path.getPath());
		//获得该目录下的所有文件
		File[] subfiles=path.listFiles();
		if(subfiles==null||subfiles.length==0) {
			return;
		}else {
			for(File file :subfiles) {
				//判断该数据是文件夹还是文件
				if(file.isDirectory()) {
					//递归
					search(file);
				}else {
					//判断文件的后缀名是不是.exe
					if(file.getName().endsWith(prefix)) {
						cont++;
					}
				}
			}
		}
	}

	
}
class childThrea implements Callable<Integer>{

	public Integer call() throws Exception {
		int ret=0;
		
		for(int i=0;i<1000;i++) {
			ret++;
		}
		return ret;
	}
	
}