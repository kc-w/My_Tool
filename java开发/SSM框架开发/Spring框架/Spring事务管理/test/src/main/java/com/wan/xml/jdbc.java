package com.wan.xml;

import org.springframework.jdbc.core.JdbcTemplate;

public class jdbc {

    //声明JdbcTemplate属性及其setter方法
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    //姓名调换
    public void change(int one,String Oname,int two,String Tname){
        this.jdbcTemplate.update("UPDATE a set a_name=? WHERE a_id=?",Tname,one);
        this.jdbcTemplate.update("UPDATE a set a_name=? WHERE a_id=?",Oname,two);
    }
}
