package com.wan.factorybean;

import com.wan.jdk.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试类
public class ProxyFactoryBeanTest {
    public static void main(String[] args) {
        String xmlpath="applicationContext.xml";
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext(xmlpath);
        //从Spring容器获得内容
        UserDao userDao=(UserDao)applicationContext.getBean("userDaoProxy");
        //执行方法
        userDao.addUser();
        userDao.deleteUser();
    }
}
