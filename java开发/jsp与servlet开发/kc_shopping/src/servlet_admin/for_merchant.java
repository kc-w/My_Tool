package servlet_admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servlet_admin/for_merchant")
@ServletSecurity(@HttpConstraint(rolesAllowed="mwrchat_admin",transportGuarantee=TransportGuarantee.CONFIDENTIAL))
public class for_merchant extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public for_merchant() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("name", "merchant");
		request.getRequestDispatcher("/admin/merchant_admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
