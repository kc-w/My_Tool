package com.wan.demo1;

import org.springframework.stereotype.Repository;

@Repository("userDao")//用于将数据访问层(DAO层)的类标识为Spring中的Bean
public class UserDaoImpl{
    public void writer() {

        System.out.println("userDao。。。。。");

    }
}
