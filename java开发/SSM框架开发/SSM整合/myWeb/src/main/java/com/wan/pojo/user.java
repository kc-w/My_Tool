package com.wan.pojo;


import java.util.List;

public class user{
    private int user_id;
    private String user_name;
    private String user_image;
    private int user_level;
    private String user_number;
    private String user_password;
    private String user_email;
    private String user_register;
    private List<blog> blogList;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public int getUser_level() {
        return user_level;
    }

    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_register() {
        return user_register;
    }

    public void setUser_register(String user_register) {
        this.user_register = user_register;
    }

    @Override
    public String toString() {
        return user_id+" "+user_name+" "+user_level+" "+user_number+" "+user_password+" "+user_email+" "+user_register;
    }
}
