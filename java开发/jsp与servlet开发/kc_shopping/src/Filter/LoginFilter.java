package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//是否登录过滤器
public class LoginFilter implements Filter {

	//初始化过滤器,使用FilterConfig对象可以得到xml的配置信息
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	//过滤方法
	public void doFilter(ServletRequest request, ServletResponse response,		
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		HttpSession session=req.getSession();
		String isLogin=(String)session.getAttribute("isLogin");
		if(isLogin!=null){
			chain.doFilter(request, response);
		}else{
			res.sendRedirect("Login.jsp");
		}

	}

	
	//释放过滤器使用的资源
	public void destroy() {
			

	}

}
