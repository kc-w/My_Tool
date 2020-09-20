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


@WebServlet("/servlet_merchant/merchant_password")
public class merchant_password extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String regEr = "[1-9]";
		String regEz = "[a-z]";
		String regEq = "[A-Z]";
		String all="^[0-9a-zA-Z]{1}[0-9a-zA-Z]{7,13}$";
		String password=request.getParameter("password");
		
		Pattern r = Pattern.compile(regEr);
		Matcher matcherr = r.matcher(password);
		boolean rmm = matcherr.find();
		
		Pattern z = Pattern.compile(regEz);
		Matcher matcherz= z.matcher(password);
		boolean zmm = matcherz.find();
		
		Pattern q = Pattern.compile(regEq);
		Matcher matcherq = q.matcher(password);
		boolean qmm = matcherq.find();
		
		Pattern a = Pattern.compile(all);
		Matcher matchera = a.matcher(password);
		boolean al = matchera.matches();
		
		String result="";
		
		if("".equals(password)||password==null){
			result="{\"result\":\"密码未填写\"}";
		}else if(password.length()<=7||password.length()>=15) {
			result="{\"result\":\"密码长度要在7-15之间(不包含)\"}";
		}else if(al){
			if(rmm&&zmm&&qmm) {
				result="{\"result\":\"qmm\"}";
			}else if(rmm&&zmm) {
				result="{\"result\":\"zmm\"}";
			}else {
				result="{\"result\":\"rmm\"}";
			}
		}else {
			result="{\"result\":\"密码只能由大小写字母和数字组成\"}";
		}
		
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
}
