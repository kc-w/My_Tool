package servlet_customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.factory.DaoFactory;
import Dao.interfaces.usersDao_interface;

//判断用户名是否存在
@WebServlet("/servlet_customer/user_email")
public class user_email extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String regEx = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
		String email=request.getParameter("email");
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(email);
		boolean rs = matcher.matches();
		
		String result="";
		try {
			usersDao_interface userDao=DaoFactory.getUsersDao();
			boolean rsDao=userDao.selectE(email);
			if(rsDao) {
				result="{\"result\":\"该邮箱已被使用\"}";
			}else if("".equals(email)||email==null){
				result="{\"result\":\"邮箱未填写\"}";
			}else if(email.length()<=5||email.length()>=30) {
				result="{\"result\":\"邮箱长度要在10-30之间(不包含)\"}";
			}else if(rs){
				result="{\"result\":\"ok\"}";
			}else {
				result="{\"result\":\"邮箱格式不正确\"}";
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

}