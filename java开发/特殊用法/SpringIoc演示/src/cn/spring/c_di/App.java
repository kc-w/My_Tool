package cn.spring.c_di;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {

	
	
	@Test
	public void testApp() {
		// 创建容器对象
//		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/spring/c_di/bean.xml");	
		
	
		// 从当前包下面  找applicationContext.xml
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
	
		
		User user  = (User) ac.getBean("user");
	//	System.out.println(user.toString());
//		user.test();
		
		System.out.println(user);
		
	}
}
