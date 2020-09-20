package com.wan;

public class obj {
    private Integer a_id;
    private String a_name;

    public obj(){

    }
    public obj(Integer a_id,String a_name){
        this.a_id=a_id;
        this.a_name=a_name;
    }

    public Integer getId(){
     return a_id;
    }
    public void setId(Integer a_id){
        this.a_id=a_id;
    }
    public String getName(){
        return a_name;
    }
    public void setName(String a_name){
        this.a_name=a_name;
    }
    public String toString(){
        return "id="+a_id+"\t"+"name="+a_name;
    }
}
