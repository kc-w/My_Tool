package cn.spring.b_create_obj;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	@Test
	public void testApp() {
		// 创建容器对象
//		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/spring/b_create_obj/applicationContext.xml");
		
		
		// 从当前包下面  找applicationContext.xml
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml",this.getClass());
	/*	
		String  str  = (String) ac.getBean("str");
		
		System.out.println("-----------"+str);
		*/
		
		User user  = (User) ac.getBean("user");
		
		System.out.println(""+ user);
	}
	
	@Test
	public void testUserFactory() {
		// 创建容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/spring/b_create_obj/bean2.xml");
		
		User user  = (User) ac.getBean("user");
		
//		user.toString();
//		System.out.println(user.toString());
		
	}
	
}
