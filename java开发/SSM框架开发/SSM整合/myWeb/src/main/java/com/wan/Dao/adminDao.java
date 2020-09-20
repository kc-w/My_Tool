package com.wan.Dao;



import com.wan.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface adminDao {
    //登录验证
    public admin login(@Param("number") String admin_number, @Param("password") String admin_password);

    //修改用户信息
    public int change(user user);

    //查看用户列表
    public List<user> selectUserList();

    //查看评论列表
    public List<comment> selectComList();

    //根据id删除评论
    public int deleteComment(@Param("comment_id") int comment_id);

    //查看博客列表
    public List<blog> selectBlogList();

    //根据id删除博客
    public int deleteBlog(@Param("blog_id") int blog_id);

    //查看用户活跃信息列表
    public List<active> selectActiveList();

    //新增管理员活跃列表
    public int addActive(a_active a_active);

    //管理员执行的操作记录
    public List<management> selectManagement();

    //管理员执行的操作
    public int addManagement(management management);

    //删除用户
}
