package com.wan.test;

import com.wan.mybatisUtils.mybatisUtils;
import com.wan.pojo.User;
import com.wan.pojo.order_user;
import com.wan.pojo.orders;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class test {


    @Test
    public void run2(){
        SqlSession sqlSession= mybatisUtils.getSession();
        User user=sqlSession.selectOne("com.wan.test.UserMapper.findU2",1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void run3(){
        SqlSession sqlSession= mybatisUtils.getSession();
        order_user user=sqlSession.selectOne("com.wan.test.UserMapper.findUserWithOrders",1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void run4(){
        SqlSession sqlSession= mybatisUtils.getSession();
        orders orders=sqlSession.selectOne("com.wan.test.OrdersMapper.findOrdersWithProduct",1);
        System.out.println(orders);
        sqlSession.close();
    }

}
