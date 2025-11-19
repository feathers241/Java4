package Servlets;

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
import Entity.Favorite;
import Entity.Users;
import Entity.Video;

@WebServlet({"/Bai3","/Bai3/Keyword"})
public class Bai3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Bai3() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/Bai3.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDao vdao = new VideoDaoImpl();
		String keyword = request.getParameter("keyword");
		String path = request.getServletPath();
		if(path.contains("Keyword") && keyword != null) {
			List list = new ArrayList();
			for(Video video : vdao.findall()) {
				if(video.getTitle().contains(keyword)) {
					list.add(video);
				}
			}
			request.setAttribute("list", list);
		}else {
			request.setAttribute("mess", "Không tìm thấy title có chứ từ khóa trên");
		}

		request.getRequestDispatcher("/views/Bai3.jsp").forward(request, response);
	}

}
