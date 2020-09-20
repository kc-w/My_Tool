package com.wan.pojo;

public class User {
    private Integer id;
    private String name;
    private Integer age;
    private Uid card;

    public Integer getA_id() {
        return id;
    }

    public void setA_id(Integer a_id) {
        this.id = a_id;
    }

    public String getA_name() {
        return name;
    }

    public void setA_name(String a_name) {
        this.name = a_name;
    }

    public Integer getA_age() {
        return age;
    }

    public void setA_age(Integer a_age) {
        this.age = a_age;
    }

    public Uid getCard() {
        return card;
    }

    public void setCard(Uid card) {
        this.card = card;
    }

    public String toString() {
        return "id:"+id+"  name:"+name+" age:"+age+" card:"+card;
    }



}
