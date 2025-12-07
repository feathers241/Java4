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
import Dao.HistoryDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.CategoryDaoImpl;
import DaoImpl.HistoryDaoImpl;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Category;
import Entity.History;
import Entity.Users;
import Entity.Video;

@WebServlet({"/HistoryManager","/HistoryManager/Create","/HistoryManager/Update","/HistoryManager/Delete","/HistoryManager/Reset"})
public class HistoryManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HistoryManager() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDao vdao = new VideoDaoImpl();
		CategoryDao cdao = new CategoryDaoImpl();
		HistoryDao hdao = new HistoryDaoImpl();
		
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
			int hisid = Integer.parseInt(videoid);
			History history = hdao.findIntegerId(hisid);
			request.setAttribute("Youtubeid", history.getId());
			request.setAttribute("VideoTitle", history.getUser().getId());
			request.setAttribute("ViewCount", history.getVideo().getId());
			request.setAttribute("Description", history.getViewDate());
		}
				//Chuyển trang
				int count = hdao.findall().size();   // lấy tổng số video
				int last = (int) Math.ceil(count / 6.0); // tính trang cuối đúng

				request.setAttribute("last", last);		
				String pageParam = request.getParameter("page");
				int page = (pageParam == null) ? 1 : Integer.parseInt(pageParam);
				if (page < 1) page = 1;
				if (page > last) page = last;

				List<History> allvideo = hdao.findVideosByPage(page, 6);
				request.setAttribute("VideoList", allvideo);
				request.setAttribute("page", page);

				String ListDisplay = request.getParameter("display") != null ? request.getParameter("display") : "none";
				String EditorDisplay = request.getParameter("display") == null ? "block" : "none";
				request.setAttribute("ListDisplay", ListDisplay);
				request.setAttribute("EditorDisplay", EditorDisplay);
				
		request.getRequestDispatcher("/Admin/History.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDao vdao = new VideoDaoImpl();
		CategoryDao cdao = new CategoryDaoImpl();
		UserDao udao = new UserDaoImpl();
		HistoryDao hdao = new HistoryDaoImpl();
		
		
		//Video List
		request.setAttribute("VideoTotal", vdao.findall().size());
		request.setAttribute("VideoList", vdao.findall());
		
		//Category list
		request.setAttribute("catlist", cdao.findall());
		
		/// Hiển thị info video sau khi nhấn vào edit tronng video List
		String getuser = request.getParameter("VideoTitle");
		String getvideo = request.getParameter("ViewCount");
		Users newuser = udao.findById(getuser);
		Video newvideo = vdao.findById(getvideo);
		
		//Path
		String path = request.getServletPath();
		
		History history = new History(newuser,newvideo);
			//Create
			if(path.contains("Create")) {
				hdao.create(history);
			}
			//Update
			if(path.contains("Update")) {
				hdao.update(history);
				request.setAttribute("VideoTitle", history.getUser());
				request.setAttribute("ViewCount", history.getVideo());
			}
			//Delete
			if(path.contains("Delete")) {
				hdao.delete(history);
			}
			//Reset
			if(path.contains("Reset")) {
				doGet(request, response);
			}
		
		request.getRequestDispatcher("/Admin/History.jsp").forward(request, response);
	}

}
