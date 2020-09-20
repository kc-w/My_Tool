package servlet_merchant;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.merchant.merchant;
import Bean.sp.goods;
import Dao.factory.DaoFactory;
import Dao.interfaces.merchantDao_interface;


@WebServlet("/servlet_merchant/addshop")
public class addshop extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		merchant merchant=(merchant)request.getSession().getAttribute("merchant");
		
		String name=request.getParameter("name");
		float price=Float.parseFloat(request.getParameter("price"));
		int kc=Integer.parseInt(request.getParameter("kc"));
		goods goods=new goods();
		goods.setPname(name);
		goods.setPprice(price);
		goods.setPstock(kc);
		goods.setMid(merchant.getMid());
		try {
			String result="";
			merchantDao_interface merchantDao = DaoFactory.getmerchantDao();
			boolean mark=merchantDao.addP(goods);
			if(mark) {
				result="{\"result\":\"添加成功\"}";
			}else {
				result="{\"result\":\"添加失败\"}";
			}
			response.setContentType("text/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
