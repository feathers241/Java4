package Servlets.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Dao.UserDao;
import DaoImpl.UserDaoImpl;
import Entity.Users;
import Entity.Video;

/**
 * Servlet implementation class UserManager
 */
@WebServlet({"/UserManager","/UserManager/Create","/UserManager/Delete","/UserManager/Update","/UserManager/Reset"})
public class UserManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserManager() {
        super(); 
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao udao = new UserDaoImpl();
		// Lấy adminid từ indexAdmin
		String adminid = request.getParameter("adminid");
		request.setAttribute("adminid", adminid);
		//Số lượng User
		request.setAttribute("UserTotal", udao.findall().size());
		
		//Hiển thị lên editor khi nhấn vào Edit
		String userid = request.getParameter("userid");
		if(userid!= null) {
			Users user = udao.findById(userid);
			request.setAttribute("id", user.getId());
			request.setAttribute("password", user.getPassword());
			request.setAttribute("fullname", user.getFullname());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("isAdmin", user.isAdmin());
		}
		
		//Chuyển trang
		int count = udao.findall().size();   // lấy tổng số video
		int last = (int) Math.ceil(count / 6.0); // tính trang cuối đúng

		request.setAttribute("last", last);		
		String pageParam = request.getParameter("page");
		int page = (pageParam == null) ? 1 : Integer.parseInt(pageParam);
		if (page < 1) page = 1;
		if (page > last) page = last;

		List<Users> allvideo = udao.find6User(page, 6);
		request.setAttribute("UserList", allvideo);
		request.setAttribute("page", page);
		
		String ListDisplay = request.getParameter("display") != null ? "block" : "none";
		String EditDisplay = request.getParameter("display") == null ? "block" : "none";
		
		
		request.setAttribute("ListDisplay", ListDisplay);
		request.setAttribute("EditDisplay", EditDisplay);
		
		request.getRequestDispatcher("/Admin/UserManager.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao udao = new UserDaoImpl();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		Boolean isAdmin = Boolean.parseBoolean(request.getParameter("role"));
		
		//CRUD
		if(id != null) {
			Users user = new Users(id,password,fullname,email,isAdmin);
			String path = request.getServletPath();
			if(path.contains("Create")) {
				udao.create(user);
			}
			if(path.contains("Delete")) {
				udao.delete(user);
			}
			if(path.contains("Update")) {
				udao.update(user);
				request.setAttribute("id", user.getId());
				request.setAttribute("password", user.getPassword());
				request.setAttribute("fullname", user.getFullname());
				request.setAttribute("email", user.getEmail());
				request.setAttribute("isAdmin", user.isAdmin());
			}
			if(path.contains("Reset")) {}
			
			//Số lượng User
			request.setAttribute("UserTotal", udao.findall().size());
			
			//Chuyển trang
			int count = udao.findall().size();   // lấy tổng số video
			int last = (int) Math.ceil(count / 6.0); // tính trang cuối đúng

			request.setAttribute("last", last);		
			String pageParam = request.getParameter("page");
			int page = (pageParam == null) ? 1 : Integer.parseInt(pageParam);
			if (page < 1) page = 1;
			if (page > last) page = last;

			List<Users> allvideo = udao.find6User(page, 6);
			request.setAttribute("UserList", allvideo);
			request.setAttribute("page", page);
			
			String ListDisplay = request.getParameter("display") != null ? "block" : "none";
			String EditDisplay = request.getParameter("display") == null ? "block" : "none";
			
			
			request.setAttribute("ListDisplay", ListDisplay);
			request.setAttribute("EditDisplay", EditDisplay);
			
		}
		request.getRequestDispatcher("/Admin/UserManager.jsp").forward(request, response);
	}

}
