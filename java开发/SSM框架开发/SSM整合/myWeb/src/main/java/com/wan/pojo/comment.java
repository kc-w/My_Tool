package com.wan.pojo;

import java.util.List;

public class comment {
    private int comment_id;
    private int user_id;
    private int blog_id;
    private String comment_body;
    private String comment_time;
    private List<blog> blogList;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public String getComment_body() {
        return comment_body;
    }

    public void setComment_body(String comment_body) {
        this.comment_body = comment_body;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }


    public List<blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<blog> blogList) {
        this.blogList = blogList;
    }
}
