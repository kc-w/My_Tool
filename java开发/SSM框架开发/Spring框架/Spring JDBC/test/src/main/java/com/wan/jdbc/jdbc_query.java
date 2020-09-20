package com.wan.jdbc;

import com.wan.obj;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;


public class jdbc_query {

    //声明JdbcTemplate属性及其setter方法
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    //通过id查询账号数据信息
    public obj findById(int id){
        String sql="select * from a where a_id=?";
        //创建一个新的BeanPropertyRowMapper对象:自动得将数据表中的数据映射到用户自定义的类中(前提是自定义类的字段必须要和表字段相同)
        RowMapper<obj> rowMapper=new BeanPropertyRowMapper<obj>(obj.class);

        //将id绑定到sql语句中,并通过RowMapper返回一个Object类型的单行记录
        return this.jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    //查询所有的账户信息
    public List<obj> findAll(){
        String sql="select * from a";
        RowMapper<obj> rowMapper=new BeanPropertyRowMapper<obj>(obj.class);

        //执行静态sql查询,并通过RowMapper返回结果
        return this.jdbcTemplate.query(sql,rowMapper);
    }
}
