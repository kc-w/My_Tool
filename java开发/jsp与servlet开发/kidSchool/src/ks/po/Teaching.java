package ks.po;
//课程信息类
public class Teaching {
    //课程信息id
    private Integer id;
    //幼儿id
    private Integer kidId;
    //教师id
    private Integer teacherId;
    //上课时间
    private String teachDate;
    //上课内容
    private String content;
    //上课效果
    private String effect;


    public String getTeachDate() {
        return teachDate;
    }

    public void setTeachDate(String teachDate) {
        this.teachDate = teachDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKidId() {
        return kidId;
    }

    public void setKidId(Integer kidId) {
        this.kidId = kidId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
