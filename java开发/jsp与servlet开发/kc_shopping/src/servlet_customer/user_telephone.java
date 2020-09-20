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
@WebServlet("/servlet_customer/user_telephone")
public class user_telephone extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String regEx = "^1[0-9][0-9]\\d{8}$";
		String telephone=request.getParameter("telephone");
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(telephone);
		boolean rs = matcher.matches();
		
		String result="";
		try {
			usersDao_interface userDao=DaoFactory.getUsersDao();
			boolean rsDao=userDao.selectT(telephone);
			if(rsDao) {
				result="{\"result\":\"号码已被使用\"}";
			}else if("".equals(telephone)||telephone==null){
				result="{\"result\":\"号码未填写\"}";
			}else if(rs){
				result="{\"result\":\"ok\"}";
			}else {
				result="{\"result\":\"号码格式不正确\"}";
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