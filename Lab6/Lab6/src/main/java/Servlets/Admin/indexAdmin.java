package Servlets.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Dao.FavoriteDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.FavoriteDaoImpl;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Users;
import Entity.Video;

/**
 * Servlet implementation class indexAdmin
 */
@WebServlet("/indexAdmin")
public class indexAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L; 
       
    public indexAdmin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao udao = new UserDaoImpl();
		VideoDao vdao = new VideoDaoImpl();
		FavoriteDao fdao = new FavoriteDaoImpl();
		//Lấy userid từ Login
//		String adminid = request.getParameter("adminid");
		String adminid = "admin02";
		
		//Lấy fullname hiển thị lên trang chủ 
		Users user = udao.findById(adminid);
		request.setAttribute("fullname", user.getFullname());
		
		//Chuyển adminid sang jsp servlet khác :
		request.setAttribute("adminid", adminid);
		
		//Lấy tổng số video 
		request.setAttribute("VideoTotal", vdao.findall().size());
		
		//Lấy tổng user
		request.setAttribute("UserTotal", udao.findall().size());
		
		//Tổng số video like
		request.setAttribute("FavoriteTotal", fdao.findall().size());
		
		//Tống số báo cáo

		
		//Các video
		request.setAttribute("VideoList", vdao.findall());
		
		
		request.getRequestDispatcher("/Admin/index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
