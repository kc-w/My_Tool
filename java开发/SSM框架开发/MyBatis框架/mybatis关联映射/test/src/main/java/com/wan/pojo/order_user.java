package com.wan.pojo;

import java.util.List;

public class order_user {
    private Integer id;
    private String username;
    private String address;
    private List<orders> orderList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<orders> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<orders> orderList) {
        this.orderList = orderList;
    }

    public String toString(){
        return "id:"+id+" username:"+username+" address:"+address+" orderlist:"+orderList;
    }
}
