package com.wan.pojo;

public class Uid {

    private Integer id;
    private Integer id_card;

    public Integer getU_id() {
        return id;
    }

    public void setU_id(Integer id) {
        this.id = id;
    }

    public Integer getId_card() {
        return id_card;
    }

    public void setId_card(Integer id_card) {
        this.id_card = id_card;
    }

    public String toString(){
        return "id:"+id+" card:"+id_card;
    }
}
