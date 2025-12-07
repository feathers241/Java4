package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import Dao.UserDao;
import DaoImpl.UserDaoImpl;
import Entity.Users;

/**
 * Servlet implementation class Bai1
 */
@WebServlet({"/account"})

public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/Bai1.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDao udao = new UserDaoImpl();
		Users user = udao.findById(username);
		if(user == null) {
			request.setAttribute("mess", "Không tìm thấy tài khoản");
		}else {
			request.getSession().setAttribute("user", user);
			request.setAttribute("mess", "Đăng nhập thành công");
		}
		HttpSession session = request.getSession();
		String secureURL = (String) session.getAttribute("secureURL");
		if(secureURL!=null) {
			response.sendRedirect(secureURL);
			return;
		}
		request.getRequestDispatcher("/views/Bai1.jsp").forward(request, response);
	}
}
