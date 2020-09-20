package com.wan.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {

    public static void main(String[] args) {
        //加载文件路径
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean1.xml");

        //获取UserController实例
        UserController userController=(UserController)applicationContext.getBean("userController");

        //调用UserController中的方法
        userController.writer();


    }

}
