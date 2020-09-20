package com.wan.jdbc;

import com.wan.obj;
import org.springframework.jdbc.core.JdbcTemplate;

public class jdbc_update {
    //声明JdbcTemplate属性及其setter方法
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    //添加
    public int addUser(obj o){
        String sql="insert into a(a_name) values(?)";

        //定义数组存储sql语句中的参数
        Object[] objs=new Object[]{
                o.getName()
        };

        //执行添加操作,返回的是受sql语句影响的记录条数
        int num=this.jdbcTemplate.update(sql,objs);
        return num;
    }

    //更新
    public int updateUser(obj o){
        String sql="update a set a_name=? where a_id=?";

        //定义数组存储sql语句中的参数
        Object[] objs=new Object[]{
                o.getId(),
                o.getName()
        };

        //执行更新操作,返回的是受sql语句影响的记录条数
        int num=this.jdbcTemplate.update(sql,objs);
        return num;
    }

    //删除
    public int deleteUser(int id){
        String sql="delete from a where a_id=?";
        //执行删除操作,返回的是受sql语句影响的记录条数
        int num=this.jdbcTemplate.update(sql,id);
        return num;
    }

}
