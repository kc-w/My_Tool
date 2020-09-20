package cn.spring.b_create_obj;

public class UserFactory {

	//非静态
	public User getInstace() {
		return new User(122,"非静态 工厂实例 创建对象");
	}
	
	
	//静态
	public static User getStaticInstace() {
		
		return new User(144, "静态方式 创建对象");
	}
	
}
