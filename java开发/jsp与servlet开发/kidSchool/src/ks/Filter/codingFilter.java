package ks.Filter;

import javax.servlet.*;
import java.io.IOException;

//编码过滤器
public class codingFilter implements Filter {

    private String encoding;

    //初始化过滤器,使用FilterConfig对象可以得到xml的配置信息
    public void init(FilterConfig filterConfig) throws ServletException {
        //从xml中获取编码方式
        encoding = filterConfig.getInitParameter("encoding");

    }

    //过滤方法
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);

    }


    //释放过滤器使用的资源
    public void destroy() {
    }
}