package com.wan.service.impl;

import com.wan.Dao.adminDao;
import com.wan.pojo.*;
import com.wan.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//业务实现层
@Service("adminService")//业务层bean注解
@Transactional//事务注解
public class adminServiceImpl implements adminService {

    @Autowired//根据类型来查找和装配元素
    private adminDao adminDao;


    @Override
    public admin login(String admin_number, String admin_password) {
        admin admin=adminDao.login(admin_number,admin_password);
        return admin;
    }

    @Override
    public int change(user user) {
        int row=change(user);
        return row;
    }

    @Override
    public List<user> selectUserList() {
        List<user> users=adminDao.selectUserList();
        return users;
    }

    @Override
    public List<comment> selectComList() {
        List<comment> comments=adminDao.selectComList();
        return comments;
    }

    @Override
    public int deleteComment(int comment_id) {
        int row=adminDao.deleteComment(comment_id);
        return row;
    }

    @Override
    public List<blog> selectBlogList() {
        List<blog> blogs=adminDao.selectBlogList();
        return blogs;
    }

    @Override
    public int deleteBlog(int blog_id) {
        int row=adminDao.deleteBlog(blog_id);
        return row;
    }

    @Override
    public List<active> selectActiveList() {
        List<active> actives=adminDao.selectActiveList();
        return actives;
    }

    @Override
    public int addActive(a_active a_active) {
        int row=adminDao.addActive(a_active);
        return row;
    }

    @Override
    public List<management> selectManagement() {
        List<management> managements=adminDao.selectManagement();
        return managements;
    }

    @Override
    public int addManagement(management management) {
        int row=adminDao.addManagement(management);
        return row;
    }
}
