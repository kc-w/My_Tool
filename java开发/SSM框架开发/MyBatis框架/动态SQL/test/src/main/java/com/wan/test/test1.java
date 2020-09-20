package com.wan.test;

import com.wan.mybatisUtils.mybatisUtils;
import com.wan.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class test1 {

    public static void findUser(){

        SqlSession sqlSession= mybatisUtils.getSession();

        User user=new User();
        user.setA_name("游");
        user.setA_age(null);

        List<User> users=sqlSession.selectList("com.wan.test.UserMapper.findU",user);

        for (User users1:users){
            System.out.println(users1);
        }

        sqlSession.close();

    }

    public static void findUser1(){

        SqlSession sqlSession= mybatisUtils.getSession();

        User user=new User();
        user.setA_name("游");
        user.setA_age(null);

        List<User> users=sqlSession.selectList("com.wan.test.UserMapper.findU1",user);

        for (User users1:users){
            System.out.println(users1);
        }

        sqlSession.close();

    }

    public static void update1(){

        SqlSession sqlSession= mybatisUtils.getSession();

        User user=new User();
        user.setA_name("游");
        user.setA_age(9);
        user.setA_id(10);

        int rows=sqlSession.update("com.wan.test.UserMapper.update1",user);
        if(rows>0){
            System.out.println("更新了"+rows+"条记录");
        }else {
            System.out.println("失败");
        }
        sqlSession.commit();
        sqlSession.close();

    }

    public static void findUser2(){

        SqlSession sqlSession= mybatisUtils.getSession();

        List<Integer> ids=new ArrayList<Integer>();
        ids.add(1);
        ids.add(6);
        List<User> users=sqlSession.selectList("com.wan.test.UserMapper.findU4",ids);

        for (User user:users){
            System.out.println(user);
        }
        sqlSession.close();

    }

    public static void findUser3(){

        SqlSession sqlSession= mybatisUtils.getSession();

        User user=new User();
        user.setA_name("j");
        List<User> users=sqlSession.selectList("com.wan.test.UserMapper.findU5",user);
        for (User user1:users){
            System.out.println(user1);
        }
        sqlSession.close();

    }

    public static void main(String[] args){
        findUser();
        findUser1();
        update1();
        findUser2();
        findUser3();
    }

}
