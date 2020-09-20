package com.wan.pojo;

public class management {
    private int m_id;
    private int admin_id;
    private int user_id;
    private String m_body;
    private String m_time;

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getM_body() {
        return m_body;
    }

    public void setM_body(String m_body) {
        this.m_body = m_body;
    }

    public String getM_time() {
        return m_time;
    }

    public void setM_time(String m_time) {
        this.m_time = m_time;
    }

    @Override
    public String toString() {
        return m_id+" "+admin_id+" "+user_id+" "+m_body+" "+m_time;
    }
}
