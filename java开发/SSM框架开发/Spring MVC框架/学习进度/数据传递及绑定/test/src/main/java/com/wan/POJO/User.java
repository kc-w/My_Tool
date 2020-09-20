package com.wan.POJO;

//使用pojo类型数据绑定时,前端请求的参数名必须要与绑定的pojo类中的属性名一样,否则后台接受到的值将为null
//简单pojo类型
public class User {
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
