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

//判断用户名是否存在
@WebServlet("/servlet_customer/user_username")
public class user_username extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String regEx = "^[\\u4E00-\\u9FA5A-Za-z0-9]{1,18}$";
		String username=request.getParameter("username");
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(username);
		boolean rs = matcher.matches();
		
		String result="";
		
		
			if(username.equals("")) {
				result="{\"result\":\"该用户名已被使用\"}";
			}else if("".equals(username)||username==null){
				result="{\"result\":\"用户名未填写\"}";
			}else if(username.length()<=1||username.length()>=20) {
				result="{\"result\":\"用户名长度要在0-19之间(不包含)\"}";
			}else if(rs){
				result="{\"result\":\"ok\"}";
			}else {
				result="{\"result\":\"用户名只能由字母,数字或汉字组成\"}";
			}
			
		
		

		
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

}
