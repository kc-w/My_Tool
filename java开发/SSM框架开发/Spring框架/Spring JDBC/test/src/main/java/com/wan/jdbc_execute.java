package com.wan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

//execute()方法执行方法内的sql语句
public class jdbc_execute {
    public static void main(String[] args) {
        String xmlpath="jdbc.xml";
        //加载配置文件
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext(xmlpath);
        //获取JdbcTemplate实例
        JdbcTemplate jdbcTemplate=(JdbcTemplate)applicationContext.getBean("jdbcTemplate");
        //使用execute()方法执行SQL语句，创建用户账号管理表c
        jdbcTemplate.execute("CREATE TABLE c(c_id int primary key,c_name varchar(10))");
    }
}