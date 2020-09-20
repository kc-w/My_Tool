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

import Bean.merchant.merchant;
import Dao.factory.DaoFactory;
import Dao.interfaces.merchantDao_interface;

@WebServlet("/servlet_merchant/merchant_number")
public class merchant_number extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String regEx = "^[0-9]{10,14}$";
		String number=request.getParameter("number");
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(number);
		boolean rs = matcher.matches();
		
		String result="";
		try {
			merchantDao_interface merchantDao=DaoFactory.getmerchantDao();
			merchant merchant=merchantDao.selectN(number);
			if(merchant.getSname()!=null) {
				result="{\"result\":\"账号已被使用\"}";
			}else if("".equals(number)||number==null){
				result="{\"result\":\"账号未填写\"}";
			}else if(number.length()<=9||number.length()>=15) {
				result="{\"result\":\"账号长度要在9-15之间(不包含)\"}";
			}else if(rs) {
				result="{\"result\":\"ok\"}";
			}else{
				result="{\"result\":\"账号格式不正确\"}";
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
