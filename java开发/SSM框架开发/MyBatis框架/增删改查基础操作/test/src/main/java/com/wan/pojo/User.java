package com.wan.pojo;

public class User {
    private Integer a_id;
    private String a_name;

    public Integer getId() {
        return a_id;
    }

    public void setId(Integer id) {
        this.a_id = id;
    }

    public String getName() {
        return a_name;
    }

    public void setName(String name) {
        this.a_name = name;
    }

    public String toString() {
        return "id:"+a_id+"  name:"+a_name;
    }
}
