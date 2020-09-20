package Listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//在线用户监听器
public class onlineListener implements ServletContextListener,HttpSessionListener,HttpSessionAttributeListener{
	
	ServletContext application=null;

	//web应用启动,即ServletContext的对象application被创建时被调用
	public void contextInitialized(ServletContextEvent arg0) {
		application=arg0.getServletContext();
		ArrayList<Object> online=new ArrayList<Object>();
		//创建ArrayList集合保存用户
		application.setAttribute("online", online);

	}
	
	//web应用关闭前被调用
	public void contextDestroyed(ServletContextEvent arg0) {
		
		

	}

	//session创建时调用
	public void sessionCreated(HttpSessionEvent arg0) {
		
		
	}

	//session失效前调用
	public void sessionDestroyed(HttpSessionEvent arg0) {
		ArrayList<Object> online=(ArrayList<Object>)application.getAttribute("online");
		online.remove(arg0.getSession());
		application.setAttribute("online", online);
		
	}

	//session中添加一个新的属性时被调用
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		ArrayList<Object> online=(ArrayList<Object>)application.getAttribute("online");
		online.add(arg0.getSession());
		application.setAttribute("online", online);
	}

	//session中移除属性时被调用
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		
		
	}

	//session中属性替换时被调用
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
		
	}

}

