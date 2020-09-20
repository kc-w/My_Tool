package Condition_class;
//使用Condition类进行等待
//线程和锁的结合使用
public class main {

	public static void main(String[] args) {
		warehouse wh=new warehouse();
		add a=new add(wh);
		reduce r=new reduce(wh);
		
		a.start();
		r.start();
		
		try {
			a.join();
			r.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("仓库剩余"+wh.getNumber()+"件商品");
	}

}
