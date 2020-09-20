package 设计模式实验.单例模式;

public class Danlimoshi {
	private static Danlimoshi i = null;
	private Danlimoshi () {}
	
	public static  Danlimoshi getShili() {
		int j = 1;
		if(i==null) {
			System.out.println("第1次实例化...");
			System.out.println("这个类只能有一个实例化...");
			i = new Danlimoshi();
		}
		else {
			
			j = j + 1;
			System.out.println("第" + j + "次实例化...");
			System.out.println("此次实例化将默认采用第一个实例化...");
		}
		return i;
	}
	

}
