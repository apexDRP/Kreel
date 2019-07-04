package kreel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kreel.service.RegisterService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fname, lname, email, pw, cpw, tel, dob, no, st, ct, di, bn, bnacc;

		fname = request.getParameter("fname");
		lname = request.getParameter("lname");
		email = request.getParameter("email");
		pw = request.getParameter("pw");
		cpw = request.getParameter("cpw");
		tel = request.getParameter("tel");
		dob = request.getParameter("dob");
		no = request.getParameter("no");
		st = request.getParameter("st");
		ct = request.getParameter("ct");
		di = request.getParameter("di");
		bn = request.getParameter("bank");
		bnacc = request.getParameter("acc");
		
		RegisterService regServ = new RegisterService();
		
		regServ.addUser(fname, lname, email, pw, cpw, tel, dob, no, st, ct, di, bn, bnacc);
		String resultValidation = regServ.validateRegister();

		request.setAttribute("result", resultValidation);
		request.getRequestDispatcher("register.jsp").forward(request, response);
		
		if (resultValidation.equals("Registration Successful"))
			regServ.saveUser();

	}

}
