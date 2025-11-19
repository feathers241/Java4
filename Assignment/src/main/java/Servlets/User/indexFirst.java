package Servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Dao.CategoryDao;
import Dao.VideoDao;
import DaoImpl.CategoryDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Video;

/**
 * Servlet implementation class indexFirst
 */
@WebServlet("/indexFirst")
public class indexFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexFirst() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDao vdao = new VideoDaoImpl();
		CategoryDao cdao = new CategoryDaoImpl();
		
		request.setAttribute("list", vdao.findall());
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("id", id);	
		
		//Category 
		request.setAttribute("catlist", cdao.findall());
		String catselect = request.getParameter("category");
		
		
		//Chuyển trang
				int count = vdao.findall().size();   // lấy tổng số video
				int last = (int) Math.ceil(count / 6.0); // tính trang cuối đúng

				request.setAttribute("last", last);		
				String pageParam = request.getParameter("page");
				int page = (pageParam == null) ? 1 : Integer.parseInt(pageParam);
				if (page < 1) page = 1;
				if (page > last) page = last;

				request.setAttribute("page", page);
				List<Video> allvideo = new ArrayList<>();
				if(catselect!=null) {
					for(Video v : vdao.findVideosByPage(page, 6)) {
						if(v.getCategoryId().equals(catselect)) {
							allvideo.add(v);
						}
					}
				}else {
					allvideo = vdao.findVideosByPage(page, 6);
				}
				request.setAttribute("list", allvideo);
				
		request.getRequestDispatcher("/User/indexFirst.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
