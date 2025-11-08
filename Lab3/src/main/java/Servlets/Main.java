package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entity.UserManager;
import Entity.Users;

@WebServlet({"/Main","/Main/Create","/Main/Update","/Main/Delete","/Main/Reset","/Main/Edit"})
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager um = new UserManager();
		String path = request.getServletPath();
		
		String edit = request.getParameter("edit");
		if(edit!=null) {
			Users u = um.findById(edit);
			request.setAttribute("id", u.getId());
			request.setAttribute("fullname", u.getFullname());
			request.setAttribute("password", u.getPassword());
			request.setAttribute("email", u.getEmail());
			request.setAttribute("role", u.isAdmin());
			
			List<Users> list = new ArrayList<>();
			list.add(u);
			request.setAttribute("list", list);
		}else {
			request.setAttribute("list", um.findAll());
		}
		request.getRequestDispatcher("/views/Main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager um = new UserManager();
		String path = request.getServletPath();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("fullname");
		String email = request.getParameter("email");
		boolean role = Boolean.parseBoolean(request.getParameter("role"));
		
		Users user = new Users(id,password,name,email,role);
		
		if(path.contains("Create")) {
			um.create(user);
			request.setAttribute("mess", "Đã thêm user thành công !");
		}
		if(path.contains("Update")) {
			um.update(user);
			List<Users> list = new ArrayList<>();
			list.add(user);
			request.setAttribute("list", list);
			request.setAttribute("mess", "Đã cập nhật user thành công !");
		}
		if(path.contains("Delete")) {
			um.deleteById(user);
			request.setAttribute("mess", "Đã xóa user thành công !");
		}
		if(path.contains("Reset")) {
			request.setAttribute("list", um.findAll());
		}
		request.getRequestDispatcher("/views/Main.jsp").forward(request, response);
	}

}
