package Servlets.User;

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
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		String iduser = request.getParameter("iduser");
		Users user = dao.findById(iduser);
		request.setAttribute("fullname",user.getFullname());
		request.setAttribute("email", user.getEmail());
		request.getRequestDispatcher("/User/EditProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		String iduser = request.getParameter("iduser");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		if(fullname != null && email != null) {
			Users user = dao.findById(iduser);
			user.setFullname(fullname);
			user.setEmail(email);
			dao.update(user);
			request.setAttribute("fullname",user.getFullname());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("mess", "Cập nhật thông tin thành công !?!");
		}

		request.getRequestDispatcher("/User/EditProfile.jsp").forward(request, response);
	}

}
