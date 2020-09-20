package com.wan.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//Spring基于xml对Bean进行实例化
public class main {
    public static void main(String[] args) {
        //加载文件路径
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean.xml");

        //构造方法输出结果
        System.out.println  (applicationContext.getBean("user1"));
        //设值方式输出结果
        System.out.println(applicationContext.getBean("user2"));
    }

}
