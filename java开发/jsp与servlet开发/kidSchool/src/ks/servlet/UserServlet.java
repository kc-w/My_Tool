package ks.servlet;

import ks.dao.TeachingDAO;
import ks.dao.UserDAO;
import ks.po.Teaching;
import ks.po.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//实现查询,增加,删除用户的处理
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mark=request.getParameter("mark");
        HttpSession httpSession=request.getSession();
        UserDAO userDAO= null;

        try {
            userDAO = new UserDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(mark!=null&&mark.equals("delete")){
            int id=Integer.valueOf(request.getParameter("id"));
            try {
                userDAO.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/UserServlet");
        }

        else if(mark!=null&&mark.equals("add")){
            User user=new User();
            String role=request.getParameter("role");
            String name=request.getParameter("name");
            String pwd=request.getParameter("pwd");
            String tel=request.getParameter("tel");
            String address=request.getParameter("address");
            user.setRole(role);;
            user.setName(name);
            user.setPwd(pwd);
            user.setTel(tel);
            user.setAddress(address);
            try {
               userDAO.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/UserServlet");
        }

        else if(mark!=null&&mark.equals("change")){
            User user=new User();
            int id=Integer.valueOf(request.getParameter("changeId"));
            String role=request.getParameter("role");
            String name=request.getParameter("name");
            String pwd=request.getParameter("pwd");
            String tel=request.getParameter("tel");
            String address=request.getParameter("address");
            user.setId(id);
            user.setRole(role);;
            user.setName(name);
            user.setPwd(pwd);
            user.setTel(tel);
            user.setAddress(address);
            try {
                userDAO.change(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/UserServlet");
        }else {

            try {
                List<User> userList=userDAO.selectList();
                httpSession.setAttribute("userList",userList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            User user=(User)httpSession.getAttribute("user");
            if(user.getRole().equals("admin")){
                response.sendRedirect("/admin/userSearch.jsp");
            }else {

                try {
                    TeachingDAO teachingDAO=new TeachingDAO();
                    List<Teaching> teachingList=teachingDAO.selectList();
                    httpSession.setAttribute("teachingList",teachingList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                response.sendRedirect("/user/teachingSearch.jsp");

            }
        }

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
