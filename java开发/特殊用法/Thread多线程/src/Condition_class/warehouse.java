package Condition_class;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//Condition类可以很好的用来防止死锁
public class warehouse {

	private Lock locker=new ReentrantLock();
	
	//构建Condition类对象,cond的await()方法和signalAll()方法必须要处于lock(),unlock()块中.
	private Condition cond=locker.newCondition();
	
	//默认仓库还有10件商品
	private int number=10;
	//默认仓库最多存放100件商品
	public static final int Max_number=100;
	
	public int getNumber() {
		return number;
	}
	//商品消费操作
	public void consume(int needNumber) throws InterruptedException {
		
		locker.lock();//加锁，同一时间只能执行一个加锁的内容
		while(this.number<needNumber) {
			System.out.println("需要消费"+needNumber+"件商品,仓库还有"+this.number+"件商品,消费不成功,等待生产...");
			//locker.unlock();//如果不在此处解锁,执行到return退出后,将一直卡在这里,其他线程都将无法执行
			//return;
			cond.await();//进行等待被唤醒
		}
		this.number-=needNumber;
		cond.signalAll();//唤醒所有线程
		System.out.println("消费"+needNumber+"件商品,仓库还有"+this.number+"件商品,消费成功");
		locker.unlock();//解锁，换下一个加锁程序进行执行
		
	}
	//生产商品操作
	public void produce(int addNumber) throws InterruptedException {
		
		locker.lock();//加锁，同一时间只能执行一个加锁的内容
		while((this.number+addNumber)>Max_number) {
			System.out.println("生产"+addNumber+"件商品,仓库还有"+this.number+"件商品,超过仓库存放数目,等待消费...");
			//locker.unlock();//如果不在此处解锁,执行到return退出后,将一直卡在这里,其他线程都将无法执行
			//return;
			cond.await();//进行等待被唤醒
		}
		this.number+=addNumber;
		cond.signalAll();//唤醒所有线程
		System.out.println("生产"+addNumber+"件商品,仓库还有"+this.number+"件商品,生产成功");
		locker.unlock();//解锁，换下一个加锁程序进行执行
				
	}

}
