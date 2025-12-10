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
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			String current = request.getParameter("currentpassword");
			String newpass = request.getParameter("newpassword");
			String confirm = request.getParameter("confirmpassword");
			
			request.setAttribute("currentpassword", user.getPassword());
			if(current != null && newpass != null && confirm != null) {
				if(!user.getPassword().equals(current)) {
					request.setAttribute("mess", "Mật khẩu hiện tại không trùng khớp");
				}else {
					if(!newpass.equals(confirm)) {
						request.setAttribute("mess", "Xác nhận mật khẩu thất bại !");
					}else {
						user.setPassword(confirm);
						dao.update(user);
						response.sendRedirect(request.getContextPath() + "/indexUser");
						session.setAttribute("userid", user.getId());
						return;
					}
				}
			}
		}
		request.getRequestDispatcher("/User/ChangePassword.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/User/ChangePassword.jsp").forward(request, response);
	}

}
