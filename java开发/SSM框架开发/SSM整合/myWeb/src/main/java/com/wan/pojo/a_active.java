package com.wan.pojo;

public class a_active {
    private int a_active_id;
    private int admin_id;
    private String a_active_time;
    private String a_active_ip;

    public int getA_active() {
        return a_active_id;
    }

    public void setA_active(int a_active_id) {
        this.a_active_id = a_active_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getA_active_time() {
        return a_active_time;
    }

    public void setA_active_time(String a_active_time) {
        this.a_active_time = a_active_time;
    }

    public String getA_active_ip() {
        return a_active_ip;
    }

    public void setA_active_ip(String a_active_ip) {
        this.a_active_ip = a_active_ip;
    }

    @Override
    public String toString() {
        return a_active_id+" "+admin_id+" "+a_active_time+""+a_active_ip;
    }
}
