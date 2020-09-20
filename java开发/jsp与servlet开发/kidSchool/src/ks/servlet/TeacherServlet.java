package ks.servlet;

import ks.dao.TeacherDAO;
import ks.po.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//实现查询,增加,删除教师的处理
@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mark=request.getParameter("mark");
        HttpSession httpSession=request.getSession();
        TeacherDAO teacherDAO = null;
        try {
            teacherDAO = new TeacherDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(mark!=null&&mark.equals("delete")){
            int id=Integer.valueOf(request.getParameter("id"));
            try {
                teacherDAO.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/TeacherServlet");
        }

        else if(mark!=null&&mark.equals("add")){
            Teacher teacher=new Teacher();
            String name=request.getParameter("name");
            teacher.setName(name);
            try {
                teacherDAO.add(teacher);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/TeacherServlet");
        }

        else if(mark!=null&&mark.equals("change")){
            Teacher teacher=new Teacher();
            int id=Integer.valueOf(request.getParameter("changeId"));
            String name=request.getParameter("name");
            teacher.setId(id);
            teacher.setName(name);
            try {
                teacherDAO.change(teacher);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/TeacherServlet");
        }else {

            try {
                List<Teacher> teacherList=teacherDAO.selectList();
                httpSession.setAttribute("teacherList",teacherList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/admin/teacherSearch.jsp");

        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
