package com.wan.pojo;

import java.util.List;

public class product {
    private Integer id;
    private String name;
    private Double price;
    private List<orders> orders;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<orders> getOrders() {
        return orders;
    }

    public void setOrders(List<orders> orders) {
        this.orders = orders;
    }

    public String toString(){
        return "id:"+id+" name:"+name+" price:"+price;
    }
}
