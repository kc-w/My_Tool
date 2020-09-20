package cn.spring.a_config;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	@Test
	public void testApp() {
		// 创建容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/spring/a_config/applicationContext.xml");
		User user = (User)ac.getBean("user");
		
		User user2 = (User)ac.getBean("user");
	}
	
	
}
