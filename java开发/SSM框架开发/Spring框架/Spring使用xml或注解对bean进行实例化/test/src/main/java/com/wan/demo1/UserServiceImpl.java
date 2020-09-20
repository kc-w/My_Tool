package com.wan.demo1;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("userService")//作用在业务层(Service层),用于将业务层的类标识为Spring中的Bean
public class UserServiceImpl{

   //@Resource(name = "userDao")//用于对Bean的属性变量，属性的set(),get()方法及构造方法进行标注,
   private UserDaoImpl UserDaoImpl;

    public void writer() {
        this.UserDaoImpl.writer();
        System.out.println("userService......");
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.UserDaoImpl = userDao;
    }
}
