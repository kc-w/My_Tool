package com.wan.service;

import com.wan.pojo.active;
import com.wan.pojo.blog;
import com.wan.pojo.comment;
import com.wan.pojo.user;

import java.util.List;

//业务层接口
public interface userService {

    //验证账号
    public user jsonNumber(String number);

    //验证邮箱
    public user jsonEmail(String email);

    //验证昵称
    public user jsonName(String user_name);

    //注册用户
    public int register(user user);

    //账号密码验证
    public user login(String user_number,String user_password);

    //修改信息
    public int change(user user);

    //新增活跃信息
    public int addActive(active active);

    //发表博客
    public int addBlog(blog blog);

    //删除博客
    public int deleteBlog(int blog_id);

    //察看博客`
    public blog selectBlog(int blog_id);

    //发表评论
    public int addComment(comment comment);

    //查看所有已发表的博客
    public List<blog> selectBlogList(int user_id);

    public int selectBlogNumber(int user_id);



    //查看所有已评论的博客
    public List<blog> selectCommentList(int user_id);


}
