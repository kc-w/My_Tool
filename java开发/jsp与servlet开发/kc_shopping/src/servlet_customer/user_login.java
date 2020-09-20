package servlet_customer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.users.users;
import Dao.factory.DaoFactory;
import Dao.interfaces.usersDao_interface;


@WebServlet("/servlet_customer/user_login")
public class user_login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mark=request.getParameter("mark");
		
		if(mark.equals("login")) {
			String usernumber=request.getParameter("number");
			String password=request.getParameter("password");
			String yzm=request.getParameter("yzm");
			
			String regu = "^[0-9]{10,14}$";
			Pattern pattern1 = Pattern.compile(regu);
			Matcher matcher1 = pattern1.matcher(usernumber);
			boolean u = matcher1.matches();
			
			String regp="^[0-9a-zA-Z]{1}[0-9a-zA-Z]{7,13}$";
			Pattern pattern2 = Pattern.compile(regp);
			Matcher matcher2 = pattern2.matcher(password);
			boolean p = matcher2.matches();
			
			
			HttpSession session=request.getSession();
			if(!session.getAttribute("yzm").equals(yzm)) {
				session.setAttribute("message", "验证码错误");
				response.sendRedirect("/kc_shopping/login.jsp");
			}else {
			
				if(u&&p) {
					
					try {
						users user=new users();
						user.setUnumber(usernumber);
						user.setUpassword(password);
						usersDao_interface userDao=DaoFactory.getUsersDao();
						user=userDao.login(user);
						if(user.getUname()==null) {
							String message="账号或密码错误";
							session.setAttribute("message", message);
							response.sendRedirect("/kc_shopping/login.jsp");
						}else {
							session.setAttribute("user", user);
							response.sendRedirect("/kc_shopping/index.jsp");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					String message="账号或密码格式不符合要求";
					session.setAttribute("message", message);
					response.sendRedirect("/kc_shopping/login.jsp");
				}
			}
			
		}
		if(mark.equals("zhuce")) {
			String number=request.getParameter("number");
			String password=request.getParameter("password");
			String email=request.getParameter("email");
			String telephone=request.getParameter("telephone");
			String yzm=request.getParameter("yzm");
			
			String regn = "^[0-9]{10,14}$";
			Pattern pattern1 = Pattern.compile(regn);
			Matcher matcher1 = pattern1.matcher(number);
			boolean u = matcher1.matches();
			
			String regp="^[0-9a-zA-Z]{1}[0-9a-zA-Z]{7,13}$";
			Pattern pattern2 = Pattern.compile(regp);
			Matcher matcher2 = pattern2.matcher(password);
			boolean p = matcher2.matches();
			
			String rege = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
			Pattern pattern3 = Pattern.compile(rege);
			Matcher matcher3 = pattern3.matcher(email);
			boolean e = matcher3.matches();
			
			String regt = "^1[0-9][0-9]\\d{8}$";
			Pattern pattern4 = Pattern.compile(regt);
			Matcher matcher4 = pattern4.matcher(telephone);
			boolean t = matcher4.matches();
			
			HttpSession session=request.getSession();
			if(!session.getAttribute("yzm").equals(yzm)) {
				session.setAttribute("message", "验证码错误");
				response.sendRedirect("/kc_shopping/login.jsp");
			}
			
			
			else if(u&&p&&e&&t) {
				try {
					usersDao_interface userDao=DaoFactory.getUsersDao();
					users user=new users();
					user.setUnumber(number);
					user.setUpassword(password);
					user.setUemail(email);
					user.setUtelephone(telephone);
					if(userDao.add(user)) {
						String message="注册成功";
						session.setAttribute("message", message);
						response.sendRedirect("/kc_shopping/login.jsp");
					}else {
						String message="注册失败";

						session.setAttribute("message", message);
						response.sendRedirect("/kc_shopping/login.jsp");
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else {
				String message="非法的输入";
	
				session.setAttribute("message", message);
				response.sendRedirect("/kc_shopping/login.jsp");
			}
		}
	}

}
