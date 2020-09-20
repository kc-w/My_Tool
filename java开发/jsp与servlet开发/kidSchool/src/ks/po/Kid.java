package ks.po;
//幼儿类
public class Kid {
    //幼儿id
    private Integer id;
    //幼儿姓名
    private String name;
    //出生日期
    private String brithdate;
    //照片
    private String photo;
    //父母id
    private Integer parentsId;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrithdate() {
        return brithdate;
    }

    public void setBrithdate(String brithdate) {
        this.brithdate = brithdate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentsId() {
        return parentsId;
    }

    public void setParentsId(Integer parentsId) {
        this.parentsId = parentsId;
    }
}
