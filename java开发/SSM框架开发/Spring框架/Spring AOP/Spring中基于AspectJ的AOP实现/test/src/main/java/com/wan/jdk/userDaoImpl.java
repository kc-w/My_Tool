package com.wan.jdk;


import org.springframework.stereotype.Repository;

//目标类
@Repository("userDao")
public class userDaoImpl {

    public void addUser() {
        System.out.println("添加用户");
    }

    public void deleteUser() {
        System.out.println("删除用户");
    }
}