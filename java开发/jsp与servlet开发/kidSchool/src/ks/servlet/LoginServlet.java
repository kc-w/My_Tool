package ks.servlet;

import ks.dao.UserDAO;
import ks.po.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//实现登陆的处理
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String pw=request.getParameter("pw");
        User user=new User();
        user.setName(name);
        user.setPwd(pw);
        UserDAO userDAO=null;
        try {
            userDAO=new UserDAO();
            user=userDAO.login(user);

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (user.getName()==null){
            request.setAttribute("msg","用户账号或密码错误!");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        HttpSession httpSession=request.getSession();
        httpSession.setAttribute("user",user);


        response.sendRedirect("/UserServlet");



    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
