package com.wan.jdk;

import com.wan.aspect.MyAspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//代理类
public class JdkProxy implements InvocationHandler {
    //声明目标接口
    private UserDao userDao;
    //创建代理方法
    public Object createProxy(UserDao userDao){
        this.userDao=userDao;
        //1：类加载器
        ClassLoader classLoader=JdkProxy.class.getClassLoader();
        //2:被代理对象实现的所有接口
        Class[] classes=userDao.getClass().getInterfaces();
        //3:使用代理类，进行增强，返回的是代理后的对象
        return Proxy.newProxyInstance(classLoader,classes,this);
    }

    //所有动态代理类的方法调用，都交由invoke()方法去处理,
    //proxy被代理后的对象
    //method将被执行的方法信息(反射)
    //agrs执行方法时需要的参数
    public Object invoke(Object proxy, Method method,Object[] args)throws Throwable{
        //声明切面
        MyAspect myAspect=new MyAspect();
        //前增强
        myAspect.check_Permissions();
        //在目标类上调用方法,并传入参数
        Object obj=method.invoke(userDao,args);
        //后增强
        myAspect.log();
        return obj;
    }
}
