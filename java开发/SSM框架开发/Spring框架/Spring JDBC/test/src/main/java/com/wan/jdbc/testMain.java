package com.wan.jdbc;

import com.wan.obj;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class testMain {
    public static void main(String[] args) {

        query_id();
        //queryAll();
        //add();
    }

    public static void queryAll(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("jdbc_query.xml");
        jdbc_query query=(jdbc_query)applicationContext.getBean("query");
        List<obj> objs=query.findAll();
        for (obj o:objs){
            System.out.println(o);
        }
    }
    public static void query_id(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("jdbc_query.xml");
        jdbc_query query=(jdbc_query)applicationContext.getBean("query");
        obj objs=query.findById(1);
        System.out.print(objs);
    }
    public static void add(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("jdbc.xml");
        jdbc_update update=(jdbc_update)applicationContext.getBean("update");

        //创建obj对象,填入数据
        obj objs=new obj();
        objs.setName("游戏");

        int num=update.addUser(objs);
        if(num>0){
            System.out.print("成功");
        }else {
            System.out.print("失败");
        }
    }
}
