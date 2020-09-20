package com.wan.Controller;

import com.wan.date.Time;
import com.wan.pojo.blog;
import com.wan.pojo.page;
import com.wan.pojo.user;
import com.wan.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller//控制器注解
public class UserController {

    @Autowired//根据类型来查找和装配元素
    private userService userService;

    //用户登录,及活跃记录
    @RequestMapping(value = "login")
    public String login(user user, HttpSession httpSession){

        user user1=userService.login(user.getUser_number(),user.getUser_password());
        if(user1!=null){
            httpSession.setAttribute("user",user1);
            return "index";
        }
        return "index";
    }

    //用户注册ok
    @RequestMapping(value = "register")
    public String register(user user){
        user.setUser_name(user.getUser_number());
        user.setUser_level(1);
        user.setUser_register(new Time().getNowTime1());

        int row=userService.register(user);
        return "index";

    }

    //查看博客
    @RequestMapping(value = "selectBlog")
    public String selectBlog(int id,Model model){
        blog blog=userService.selectBlog(id);
        model.addAttribute("blog",blog);
        return "WEB-INF/jsp/user/blog";
    }

    //删除博客
    @RequestMapping(value = "deleteBlog")
    public String deleteBlog(int id){
        userService.deleteBlog(id);
       return "redirect:selectBlogList";
    }

    //评论的博客
    @RequestMapping(value = "selectComment")
    public String selectComment(user user){
        user.setUser_name(user.getUser_number());
        user.setUser_level(1);
        user.setUser_register(new Time().getNowTime1());

        int row=userService.register(user);
        return "index";

    }

    //个人信息显示ok
    @RequestMapping(value = "selectSelf")
    public String selectSelf(){
        return "WEB-INF/jsp/user/changeUser";
    }


    //个人信息修改ok
    @RequestMapping("changeUser")
    public String handleFormUpload(user user, List<MultipartFile> fileList, HttpServletRequest request, HttpSession httpSession){
        user user1=(user) httpSession.getAttribute("user");
        String filename="";
        String number=user1.getUser_number();
        //循环输出上传文件
        for (MultipartFile file:fileList){
            //设置文件保存地址
            String path=request.getServletContext().getRealPath("/image/");
            File filePath=new File(path);
            //如果保存文件的路径不存在,就先创建
            if (!filePath.exists()){
                filePath.mkdirs();
            }
            //对上传文件进行重新命名
            filename=number+"_"+(int)Math.random()*10+".jpg";

            try{
                //使用MultipartFile接口的方法完成文件上传到指定位置保存和更改文件名
                file.transferTo(new File(path+filename));

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        user.setUser_image(filename);
        user.setUser_id(user1.getUser_id());
        userService.change(user);
        user user2=userService.jsonNumber(number);
        httpSession.setAttribute("user",user2);;
        //跳转到成功页面
        return "WEB-INF/jsp/user/changeUser";

    }


    //跳转博客
    @RequestMapping(value = "writeBlog")
    public String writeBlog(){
        return "WEB-INF/jsp/user/writeBlog";
    }

   //查看发表的博客
    @RequestMapping(value = "selectBlogList")
    public String selectBlogList(HttpSession httpSession,Model model){
        user user=(user)httpSession.getAttribute("user");
        int id=user.getUser_id();

        List<blog> blogs=userService.selectBlogList(id);
        model.addAttribute("blogs",blogs);
        return "WEB-INF/jsp/user/selectMyBlog";

    }

    //查看评论过的博客




}


