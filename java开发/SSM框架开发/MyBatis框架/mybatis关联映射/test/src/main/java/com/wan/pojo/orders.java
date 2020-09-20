package com.wan.pojo;

import java.util.List;

public class orders {
    private Integer id;
    private String number;
    private List<product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<product> getProducts() {
        return products;
    }

    public void setProducts(List<product> products) {
        this.products = products;
    }

    public String toString(){
        return "id:"+id+" number:"+number+" product"+products;
    }
}
