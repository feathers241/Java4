package Servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Dao.UserDao;
import DaoImpl.UserDaoImpl;
import Entity.Users;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/User/Registration.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(username != null && fullname != null && email != null && password != null) {
			Users temp = dao.findById(username);
			if(temp != null) {
				Users user = new Users(username,password,fullname,email,false);
				dao.create(user);
				response.sendRedirect(request.getContextPath() + "/Login");
				return;
			}else {
				System.out.println("Username đã tồn tại");
			}
		}
		request.getRequestDispatcher("/User/Registration.jsp").forward(request, response);
	}

}
