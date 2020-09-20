package com.wan.demo;

import java.util.List;

//Spring基于xml对Bean进行实例化
public class User {
    private String username;
    private Integer password;
    private List<String> list;

    //使用构造注入，提供所有参数的有参构造方法
    public User(String username,Integer password,List<String> list){
        super();
        this.username=username;
        this.password=password;
        this.list=list;
    }

    //使用设值注入，提供默认空构造方法，为所有属性提供get(),set()方法
    public User(){
        super();
    }

    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(Integer password){
        this.password=password;
    }
    public void setList(List<String> list){
        this.list=list;
    }

    public String toString() {
        return "User [username="+username+",password="+password+",list="+list+"]";
    }
}
