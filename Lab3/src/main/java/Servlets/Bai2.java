package Servlets;

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
 * Servlet implementation class Bai2
 */
@WebServlet("/Bai2")
public class Bai2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bai2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/Bai2.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		try {	
				if(id!=null && password != null) {
				Users user = dao.findidemail(id);
				if(password.equals(user.getPassword())) {
					request.setAttribute("mess", "Đăng nhập thành công !");
				}else {
					request.setAttribute("mess", "Username hoặc mật khẩu không chính xác");
				}
			}
		}catch(Exception e) {
				request.setAttribute("mess", "Không được để trống thông tin đăng nhập !");
			}

		
		System.out.print(id);
		System.out.print(password);
		request.getRequestDispatcher("/views/Bai2.jsp").forward(request, response);
	}

}
