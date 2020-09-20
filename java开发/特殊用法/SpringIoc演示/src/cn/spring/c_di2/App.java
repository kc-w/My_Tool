package cn.spring.c_di2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {

	
	private  ApplicationContext ac  = new ClassPathXmlApplicationContext("cn/spring/c_di2/bean.xml");
	
	
	@Test
	public void TestApp() {
		// 从容器中获取Action实例
		UserAction userAction = (UserAction) ac.getBean("userAction");
		
		userAction.execute();
		
	}
	
}
