package 设计模式实验.简单工厂模式;
/**
 * 简单工厂模式
 * @author 小石头
 *
 */
public class Nvwa {
	public static Person show(String s) throws Exception {
		if(s == null) {
			return null;
		}
		if(s.equalsIgnoreCase("M")) {
			System.out.println("造一个男人...");
			return new Man();
		}else if(s.equalsIgnoreCase("W")) {
			System.out.println("造一个女人...");
			return new Woman();
		}else if(s.equalsIgnoreCase("R")) {
			System.out.println("造一个机器人...");
			return new Robot();
		}else
		{
			throw new Exception("对不起，造不了");
			//return null;
		}
	}
	public static void main(String[] args) throws Exception {
		Nvwa n = new Nvwa();
		Person p1 = Nvwa.show("M");
		p1.produce();
		Person p2 = Nvwa.show("W");
		p2.produce();
		Person p3 = Nvwa.show("R");
		p3.produce();
		Person p4 = Nvwa.show("n");
		p4.produce();
	}

}
