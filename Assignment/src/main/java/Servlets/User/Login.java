package Servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Users;

@WebServlet({"/Login/*"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/User/Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		String id = request.getParameter("id");

		if(id != null) {
			try {
			String password = request.getParameter("password");
			Users user = dao.findidemail(id);
			if(user == null) {
				request.setAttribute("mess", "Không tìm thấy username hoặc email hợp lệ");
			}
			if(password == null) {
				request.setAttribute("mess", "Vui lòng nhập mật khẩu !");
			}
			if(!password.equals(user.getPassword())) {
				request.setAttribute("mess", "Mật khẩu không đúng");
			}
			if(password.equals(user.getPassword())) {
				if(user.isAdmin()) {
					response.sendRedirect(request.getContextPath() + "/indexAdmin?adminid="+user.getId());
					return;
				}else {
					response.sendRedirect(request.getContextPath() + "/indexUser?userid="+user.getId());
					return;
				}
			}
			}catch(Exception e) {
				request.setAttribute("mess", "Không được để trống thông tin");
			}
		}
		request.getRequestDispatcher("/User/Login.jsp").forward(request, response);
	}

}
