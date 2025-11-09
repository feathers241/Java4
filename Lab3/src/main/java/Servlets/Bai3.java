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

/**
 * Servlet implementation class Bai3
 */
@WebServlet("/Bai3")
public class Bai3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bai3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/Bai3.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		FavoriteDao fdao = new FavoriteDaoImpl();
		VideoDao vdao = new VideoDaoImpl();

		
		String id = request.getParameter("id");
		Users user = dao.findById(id);
		
		List<Favorite> favorite = fdao.findByUserId(id);
		List<Video> list = new ArrayList();
		for(int i = 0 ; i < favorite.size() ; i++) {
			list.add(favorite.get(i).getVideo());
		}
		
		request.setAttribute("mess", "Các video yêu thích của " + user.getFullname());
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/Bai3.jsp").forward(request, response);
	}

}
