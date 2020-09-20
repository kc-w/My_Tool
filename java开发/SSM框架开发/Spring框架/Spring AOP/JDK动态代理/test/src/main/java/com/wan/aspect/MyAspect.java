package com.wan.aspect;

//切面类：可以存在多个通知Advice(增强方法：事务，日志，权限，异常)
public class MyAspect {
    public void check_Permissions(){
        System.out.println("模拟检查权限。。。");
    }
    public void log(){
        System.out.println("模拟记录日志");
    }
}
