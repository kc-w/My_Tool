package com.wan.POJO;

import java.util.List;

//封装集合属性
public class UserVO {
    private List<User> users;
    public List<User> getUsers(){
        return users;
    }
    public void setUsers(List<User> users){
        this.users=users;
    }
}
