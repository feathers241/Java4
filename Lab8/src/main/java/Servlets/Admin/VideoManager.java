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
@WebServlet({"/VideoManager","/VideoManager/Create","/VideoManager/Update","/VideoManager/Delete","/VideoManager/Reset"})
public class VideoManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public VideoManager() {
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
			Video video = vdao.findById(videoid);
			request.setAttribute("Youtubeid", video.getId());
			request.setAttribute("VideoTitle", video.getTitle());
			request.setAttribute("ViewCount", video.getViews());
			request.setAttribute("Status", video.isActive());
			request.setAttribute("Description", video.getDescription());
			request.setAttribute("VideoPoster", video.getPoster());
			request.setAttribute("catedit", video.getCategoryId());
		}
				//Chuyển trang
				int count = vdao.findall().size();   // lấy tổng số video
				int last = (int) Math.ceil(count / 6.0); // tính trang cuối đúng

				request.setAttribute("last", last);		
				String pageParam = request.getParameter("page");
				int page = (pageParam == null) ? 1 : Integer.parseInt(pageParam);
				if (page < 1) page = 1;
				if (page > last) page = last;

				List<Video> allvideo = vdao.findVideosByPage(page, 6);
				request.setAttribute("VideoList", allvideo);
				request.setAttribute("page", page);

				String ListDisplay = request.getParameter("display") != null ? request.getParameter("display") : "none";
				String EditorDisplay = request.getParameter("display") == null ? "block" : "none";
				request.setAttribute("ListDisplay", ListDisplay);
				request.setAttribute("EditorDisplay", EditorDisplay);
				
		request.getRequestDispatcher("/Admin/VideoManager.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDao vdao = new VideoDaoImpl();
		CategoryDao cdao = new CategoryDaoImpl();
		
		//Video List
		request.setAttribute("VideoTotal", vdao.findall().size());
		request.setAttribute("VideoList", vdao.findall());
		
		//Category list
		request.setAttribute("catlist", cdao.findall());
		
		/// Hiển thị info video sau khi nhấn vào edit tronng video List
		String videoid = request.getParameter("Youtubeid");
		String VideoTitle = request.getParameter("VideoTitle");
		int ViewCount = Integer.parseInt(request.getParameter("ViewCount"));
		Boolean Status = Boolean.parseBoolean(request.getParameter("Status"));
		String Description = request.getParameter("Description");
		
		//Poster
		String poster = request.getParameter("PosterName");
		request.setAttribute("poster", poster);
		String hiddenposter = request.getParameter("hiddenposter");
		if(poster==null) {
			poster = hiddenposter;
		}
		// Category 
		String Categoryid = request.getParameter("catname");
		
		//Path
		String path = request.getServletPath();
		
		Video video = new Video(videoid,VideoTitle,poster,ViewCount,Description,Status,Categoryid);
			//Create
			if(path.contains("Create")) {
				vdao.create(video);
			}
			//Update
			if(path.contains("Update")) {
				vdao.update(video);
				request.setAttribute("Youtubeid", video.getId());
				request.setAttribute("VideoTitle", video.getTitle());
				request.setAttribute("ViewCount", video.getViews());
				request.setAttribute("Status", video.isActive());
				request.setAttribute("Description", video.getDescription());
				request.setAttribute("VideoPoster", video.getPoster());
			}
			//Delete
			if(path.contains("Delete")) {
				vdao.delete(video);
			}
			//Reset
			if(path.contains("Reset")) {}
		
		request.getRequestDispatcher("/Admin/VideoManager.jsp").forward(request, response);
	}

}
