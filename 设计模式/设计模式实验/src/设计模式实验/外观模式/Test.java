package 设计模式实验.外观模式;
/**
 * 外观模式
 * @author 小石头
 *
 */
public class Test {
	public static void main(String[] args){
		Mainframe mf = new Mainframe();
		mf.on();
		
		mf.off1();
		mf.off2();
		mf.off3();
		mf.off4();
	}
}
