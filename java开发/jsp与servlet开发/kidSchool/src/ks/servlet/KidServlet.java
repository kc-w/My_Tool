package ks.servlet;

import ks.dao.KidDAO;
import ks.po.Kid;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//实现查询,增加,删除幼儿的处理
@WebServlet("/KidServlet")
public class KidServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mark=request.getParameter("mark");
        HttpSession httpSession=request.getSession();
        KidDAO kidDAO= null;
        try {
            kidDAO = new KidDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(mark!=null&&mark.equals("delete")){
            int id=Integer.valueOf(request.getParameter("id"));
            try {
                kidDAO.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.sendRedirect("/KidServlet");
        }

        else if(mark!=null&&mark.equals("add")){
            Kid kid=new Kid();
            String name=request.getParameter("name");
            String brithdate=request.getParameter("brithdate");
            int parentsId=Integer.valueOf(request.getParameter("parentsId"));
            kid.setName(name);
            kid.setBrithdate(brithdate);
            kid.setParentsId(parentsId);
            try {
                kidDAO.add(kid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/KidServlet");
        }

        else if(mark!=null&&mark.equals("change")){
            Kid kid=new Kid();
            int id=Integer.valueOf(request.getParameter("changeId"));
            String name=request.getParameter("name");
            String brithdate=request.getParameter("brithdate");
            int parentsId=Integer.valueOf(request.getParameter("parentsId"));
            kid.setId(id);
            kid.setName(name);
            kid.setBrithdate(brithdate);
            kid.setParentsId(parentsId);
            try {
                kidDAO.change(kid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/KidServlet");
        }else{


            try {
                List<Kid> kidList=kidDAO.selectList();
                httpSession.setAttribute("kidList",kidList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/admin/kidSearch.jsp");

        }



    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
