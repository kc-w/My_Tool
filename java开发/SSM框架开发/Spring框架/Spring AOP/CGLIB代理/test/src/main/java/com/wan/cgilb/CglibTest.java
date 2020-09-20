package com.wan.cgilb;

public class CglibTest {
    public static void main(String[] args){
        //创建代理对象
        CglibProxy cglibProxy=new CglibProxy();
        //创建目标对象
        UserDao userdao=new UserDao();
        //获取增强后的目标对象
        UserDao userDao1=(UserDao)cglibProxy.createProxy(userdao);
        //执行方法
        userDao1.addUser();
        userDao1.deleteUser();
    }
}
