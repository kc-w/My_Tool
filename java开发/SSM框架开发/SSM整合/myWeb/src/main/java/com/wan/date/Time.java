package com.wan.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    private Date NowTime;
    private String dataStyle1="YYY-MM-dd hh:mm:ss";
    private String dataStyle2="YYY-MM-dd";
    private SimpleDateFormat format1=new SimpleDateFormat(dataStyle1);
    private SimpleDateFormat format2=new SimpleDateFormat(dataStyle2);


    //获得格式化后的当前时间
    public String getNowTime1(){
        NowTime=new Date();
        String Time=format1.format(NowTime);
        return Time;
    }
    public String getNowTime2(){
        NowTime=new Date();
        String Time=format2.format(NowTime);
        return Time;
    }


}
