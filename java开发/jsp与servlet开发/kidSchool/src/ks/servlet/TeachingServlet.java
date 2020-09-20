package ks.servlet;

import ks.dao.TeachingDAO;
import ks.po.Teaching;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//实现查询,增加,删除,修改课程的处理
@WebServlet("/TeachingServlet")
public class TeachingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mark=request.getParameter("mark");
        HttpSession httpSession=request.getSession();
        TeachingDAO teachingDAO= null;
        try {
            teachingDAO = new TeachingDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(mark!=null&&mark.equals("delete")){
            int id=Integer.valueOf(request.getParameter("id"));
            try {
                teachingDAO.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/TeachingServlet");
        }

        else if(mark!=null&&mark.equals("add")){
            Teaching teaching=new Teaching();
            Integer kidId=Integer.valueOf(request.getParameter("kidId"));
            Integer teacherId=Integer.valueOf(request.getParameter("teacherId"));
            String teachDate=request.getParameter("teachDate");
            String content=request.getParameter("content");
            String effect=request.getParameter("effect");
            teaching.setKidId(kidId);
            teaching.setTeacherId(teacherId);
            teaching.setTeachDate(teachDate);
            teaching.setContent(content);
            teaching.setEffect(effect);
            try {
                teachingDAO.add(teaching);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/TeachingServlet");
        }

        else if(mark!=null&&mark.equals("change")){
            Teaching teaching=new Teaching();
            int id=Integer.valueOf(request.getParameter("changeId"));
            Integer kidId=Integer.valueOf(request.getParameter("kidId"));
            Integer teacherId=Integer.valueOf(request.getParameter("teacherId"));
            String teachDate=request.getParameter("teachDate");
            String content=request.getParameter("content");
            String effect=request.getParameter("effect");
            teaching.setId(id);
            teaching.setKidId(kidId);
            teaching.setTeacherId(teacherId);
            teaching.setTeachDate(teachDate);
            teaching.setContent(content);
            teaching.setEffect(effect);
            try {
                teachingDAO.change(teaching);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/TeachingServlet");
        }else {

            try {
                List<Teaching> teachingList=teachingDAO.selectList();
                httpSession.setAttribute("teachingList",teachingList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/admin/teachingSearch.jsp");

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}