package com.wan.service.impl;

import com.wan.Dao.userDao;
import com.wan.pojo.active;
import com.wan.pojo.blog;
import com.wan.pojo.comment;
import com.wan.pojo.user;
import com.wan.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//业务实现层
@Service("userService")//业务层bean注解
@Transactional//事务注解
public class userServiceImpl implements userService {

    @Autowired//根据类型来查找和装配元素
    private userDao userDao;


    @Override
    public user jsonNumber(String number) {
        user user=userDao.jsonNumber(number);
        return user;
    }

    @Override
    public user jsonEmail(String email) {
        user user=userDao.jsonEmail(email);
        return user;
    }

    @Override
    public user jsonName(String user_name) {
        return null;
    }

    @Override
    public int register(user user){
        int row=userDao.register(user);
        return row;
    }

    @Override
    public user login(String user_number, String user_password) {
        user user=userDao.login(user_number,user_password);
        return user;
    }

    @Override
    public int change(user user) {
        int row=userDao.change(user);
        return row;
    }

    @Override
    public int addActive(active active) {
        int row=userDao.addActive(active);
        return row;
    }

    @Override
    public int addBlog(blog blog) {
        int row=userDao.addBlog(blog);
        return row;
    }

    @Override
    public int deleteBlog(int blog_id) {
        int row=userDao.deleteBlog(blog_id);
        return row;
    }

    public blog selectBlog(int blog_id){
        blog blog=userDao.selectBlog(blog_id);
        return blog;
    };

    @Override
    public int addComment(comment comment) {
        int row=userDao.addComment(comment);
        return row;
    }

    @Override
    public List<blog> selectBlogList(int user_id) {
        List<blog> blogs=userDao.selectBlogList(user_id);
        return blogs;
    }

    public int selectBlogNumber(int user_id){
        int number=userDao.selectBlogNumber(user_id);
        return number;
    };


    @Override
    public List<blog> selectCommentList(int user_id) {
        List<blog> blogs=userDao.selectCommentList(user_id);
        return blogs;
    }
}
