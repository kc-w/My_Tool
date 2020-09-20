package com.wan.service;



import com.wan.pojo.*;

import java.util.List;

//业务层接口
public interface adminService {

    //登录验证
    public admin login(String admin_number, String admin_password);

    //修改用户信息
    public int change(user user);

    //查看用户列表
    public List<user> selectUserList();

    //查看评论列表
    public List<comment> selectComList();

    //根据id删除评论
    public int deleteComment(int comment_id);

    //查看博客列表
    public List<blog> selectBlogList();

    //根据id删除博客
    public int deleteBlog(int blog_id);

    //查看用户活跃信息列表
    public List<active> selectActiveList();

    //新增管理员活跃列表
    public int addActive(a_active a_active);

    //管理员执行的操作记录
    public List<management> selectManagement();

    //管理员执行操作
    public int addManagement(management management);

    //删除用户


}
