package com.wan.aspectj.xml;


import com.wan.jdk.userDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class testMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //1:从Spring容器中获得内容
        userDaoImpl userDao = (userDaoImpl) applicationContext.getBean("userDao");
        //执行方法
        userDao.addUser();
        userDao.deleteUser();
    }
}
