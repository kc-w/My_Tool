package 设计模式实验.单例模式;
/**
 * 单例模式
 * @author 小石头
 *
 */
public class Test{
	public static void main(String[] args) {
		Danlimoshi d1 = Danlimoshi.getShili();
		Danlimoshi d2 = Danlimoshi.getShili();
		
		System.out.println("判断两个实例是不是一样：" + (d1==d2));
		
		
	}

}
