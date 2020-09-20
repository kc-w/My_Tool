package servlet_merchant;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.merchant.merchant;
import Bean.sp.goods;
import Dao.factory.DaoFactory;
import Dao.interfaces.merchantDao_interface;


@WebServlet("/servlet_merchant/allshopping")
public class allshopping extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		merchant merchant=(merchant)session.getAttribute("merchant");
		List<goods> sp;
		
		try {
			merchantDao_interface merchantDao = DaoFactory.getmerchantDao();
			sp=merchantDao.selectAll(merchant.getMid());
			session.setAttribute("all", sp);
			response.sendRedirect("/kc_shopping/merchant/allshopping.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
