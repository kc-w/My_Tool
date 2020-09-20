package servlet_merchant;

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
import Dao.interfaces.merchantDao_interface;


@WebServlet("/servlet_merchant/merchant_telephone")
public class merchant_telephone extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String regEx = "^1[0-9][0-9]\\d{8}$";
		String telephone=request.getParameter("telephone");
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(telephone);
		boolean rs = matcher.matches();
		
		String result="";
		try {
			merchantDao_interface merchantDao=DaoFactory.getmerchantDao();
			boolean rsDao=merchantDao.selectT(telephone);
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
