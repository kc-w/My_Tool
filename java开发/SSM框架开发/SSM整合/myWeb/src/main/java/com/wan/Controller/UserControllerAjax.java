package com.wan.Controller;


import com.wan.date.Time;
import com.wan.pojo.admin;
import com.wan.pojo.blog;
import com.wan.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.wan.service.userService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Random;

@Controller//控制器注解
public class UserControllerAjax {

    @Autowired//根据类型来查找和装配元素
    private userService userService;


    //ajax验证注册
    @RequestMapping(value = "testRegister")
    @ResponseBody//用于返回对象,该注解用在方法上.
    public String testRegister(@RequestBody user user){//用于将请求体中的数据绑定到方法的形参中
        String number=user.getUser_number();
        String email=user.getUser_email();
        user user1=userService.jsonNumber(number);
        user user2=userService.jsonEmail(email);
        if(user1==null&&user2==null){
            return "ok";
        }
        if(user1!=null) {
            return "number";
        }
        return "email";

    }


    //ajax发送验证码验证
    @RequestMapping(value = "send_email")
    @ResponseBody//用于返回对象,该注解用在方法上.
    public String send_email(@RequestBody user user) throws MessagingException {

        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }

        //创建对象连接到邮件服务器
        Properties properties=new Properties();
        //设置发送邮件的基本参数
        //发送邮件服务器
        properties.put("mail.smtp.host","smtp.163.com");
        //发送端口
        properties.put("mail.smtp.port","25");
        properties.put("mail.smtp.auth","true");

        //设置发送邮件的账号和密码
        Session session=Session.getInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                //两个参数分别是发送邮件的账户和密码
                return new PasswordAuthentication("17607169189@163.com","999999999852wkc");
            }
        });

        //创建邮件对象
        Message message=new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("17607169189@163.com"));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getUser_email()));
        //设置邮件主题
        message.setSubject("Kc个人空间");
        //设置邮件正文,第二个参数是邮件发送的类型
        message.setContent("验证码为:"+result,"text/html;charset=UTF-8");
        //发送一封邮件
        Transport.send(message);

        return result;
    }

    //ajax验证登陆
    @RequestMapping(value = "testLogin")
    @ResponseBody//用于返回对象,该注解用在方法上.
    public String testLogin(@RequestBody user user){//用于将请求体中的数据绑定到方法的形参中
        String number=user.getUser_number();
        String password=user.getUser_password();
        user user1=userService.login(number,password);
        if(user1!=null) {
            return "ok";
        }
        return "error";
    }

    //信息修改验证
    @RequestMapping(value = "userInfoChange")
    @ResponseBody//用于返回对象,该注解用在方法上.
    public String userInfoChange(@RequestBody user user){//用于将请求体中的数据绑定到方法的形参中
        String name=user.getUser_name();
        String email=user.getUser_email();
        user user1=userService.jsonName(name);
        user user2=userService.jsonEmail(email);
        if(user1==null&&user2==null){
        return "ok";
    }
        if(user1!=null) {
        return "name";
    }
        return "email";

}

    //上传文章
    @RequestMapping(value = "upLoadBlog")
    @ResponseBody//用于返回对象,该注解用在方法上.
    public String userInfoChange(@RequestBody blog blog,HttpSession httpSession) {//用于将请求体中的数据绑定到方法的形参中
        System.out.println(blog.getBlog_body());
        user user = (user) httpSession.getAttribute("user");
        blog.setUser_id(user.getUser_id());
        blog.setBlog_time(new Time().getNowTime1());
        int row = userService.addBlog(blog);
        if (row == 1) {
            return "ok";
        }
        return "no";
    }

}
