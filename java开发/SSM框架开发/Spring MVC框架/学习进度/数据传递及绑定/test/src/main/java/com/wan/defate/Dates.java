package com.wan.defate;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;
//自定义数据绑定
public class Dates implements Converter<String,Date> {
    //定义日期格式
    private String date="yyyy-MM-dd HH:mm:ss";

    public Date convert(String source){
        //格式化日期
        SimpleDateFormat f=new SimpleDateFormat(date);
        try {
            return f.parse(source);
        }catch (Exception e){
            throw new IllegalArgumentException("aaaaaaaaa");
        }
    }
}
