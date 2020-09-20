package com.wan.pojo;

public class active {
    private int a_id;
    private int user_id;
    private String a_time;
    private String a_ip;

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getA_time() {
        return a_time;
    }

    public void setA_time(String a_time) {
        this.a_time = a_time;
    }

    public String getA_ip() {
        return a_ip;
    }

    public void setA_ip(String a_ip) {
        this.a_ip = a_ip;
    }

    @Override
    public String toString() {
        return a_id+" "+user_id+" "+a_time+""+a_ip;
    }
}
