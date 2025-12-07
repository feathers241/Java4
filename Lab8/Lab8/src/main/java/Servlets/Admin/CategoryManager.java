package Servlets.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Dao.CategoryDao;
import Dao.VideoDao;
import DaoImpl.CategoryDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Category;
import Entity.Video;

/**
 * Servlet implementation class VideoManager
 */
@WebServlet({"/CategoryManager","/CategoryManager/Create","/CategoryManager/Update","/CategoryManager/Delete","/CategoryManager/Reset"})
public class CategoryManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDao vdao = new VideoDaoImpl();
		CategoryDao cdao = new CategoryDaoImpl();
		
		// Lấy adminid từ indexAdmin
		String adminid = request.getParameter("adminid");
		request.setAttribute("adminid", adminid);
		
		//Category 
		request.setAttribute("catlist", cdao.findall());
		
		//Video List
		request.setAttribute("VideoTotal", vdao.findall().size());
		
		//Video Editor
		/// Hiển thị info video sau khi nhấn vào edit tronng video List
		String videoid = request.getParameter("videoid");
		if(videoid != null) {
			Category category = cdao.findById(videoid);
			request.setAttribute("Youtubeid", category.getId());
			request.setAttribute("VideoTitle", category.getCategoryname());
		}
				//Chuyển trang
				int count = vdao.findall().size();   // lấy tổng số video
				int last = (int) Math.ceil(count / 6.0); // tính trang cuối đúng

				request.setAttribute("last", last);		
				String pageParam = request.getParameter("page");
				int page = (pageParam == null) ? 1 : Integer.parseInt(pageParam);
				if (page < 1) page = 1;
				if (page > last) page = last;

				List<Category> allvideo = cdao.findall();
				request.setAttribute("VideoList", allvideo);
				request.setAttribute("page", page);

				String ListDisplay = request.getParameter("display") != null ? request.getParameter("display") : "none";
				String EditorDisplay = request.getParameter("display") == null ? "block" : "none";
				request.setAttribute("ListDisplay", ListDisplay);
				request.setAttribute("EditorDisplay", EditorDisplay);
				
		request.getRequestDispatcher("/Admin/CategoryManager.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDao vdao = new VideoDaoImpl();
		CategoryDao cdao = new CategoryDaoImpl();
		
		//Video List
		request.setAttribute("VideoTotal", cdao.findall().size());
		request.setAttribute("VideoList", cdao.findall());
		
		//Category list
		request.setAttribute("catlist", cdao.findall());
		
		/// Hiển thị info video sau khi nhấn vào edit tronng video List
		String videoid = request.getParameter("Youtubeid");
		String VideoTitle = request.getParameter("VideoTitle");
		
		// Category 
		String Categoryid = request.getParameter("catname");
		
		//Path
		String path = request.getServletPath();
		
		Category category = new Category(videoid,VideoTitle);
			//Create
			if(path.contains("Create")) {
				cdao.create(category);
			}
			//Update
			if(path.contains("Update")) {
				cdao.update(category);
				request.setAttribute("Youtubeid", category.getId());
				request.setAttribute("VideoTitle", category.getCategoryname());
			}
			//Delete
			if(path.contains("Delete")) {
				cdao.delete(category);
			}
			//Reset
			if(path.contains("Reset")) {}
		
		request.getRequestDispatcher("/Admin/CategoryManager.jsp").forward(request, response);
	}

}
