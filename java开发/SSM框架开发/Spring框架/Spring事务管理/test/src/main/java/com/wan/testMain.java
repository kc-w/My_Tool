package com.wan;

import com.wan.annotation.jdbc;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testMain {
    public static void main(String[] args) {
        testXML();
        //testAnnotation();
    }

    public static void testXML(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbc j=(jdbc)applicationContext.getBean("change");
        j.change(1,"朵拉",2,"莉可");
        System.out.println("ok");
    }
    public static void testAnnotation(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("annotation.xml");
        jdbc j=(jdbc)applicationContext.getBean("change");
        j.change(1,"莉可",2,"朵拉");
        System.out.println("ok");
    }
}
