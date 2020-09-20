package ks.po;
//用户类
public class User {
    //用户id
    private Integer id;
    //用户角色类型:admin系统管理员、parents家长
    private String role;
    //用户名称
    private String name;
    //用户密码;
    private String pwd;
    //用户电话
    private String tel;
    //用户地址
    private String address;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
