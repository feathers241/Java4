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
 * Servlet implementation class Bai4
 */
@WebServlet("/Bai4")
public class Bai4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bai4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FavoriteDao fdao = new FavoriteDaoImpl();
	
		List list = new ArrayList<>();
		for(Favorite fv : fdao.findall()) {
			List temp = new ArrayList<>();
			temp.add(fv.getVideo().getTitle());
			temp.add(fv.getUser().getFullname());
			temp.add(fv.getLikeDate());
			list.add(temp);
		}

		request.setAttribute("mess", "Danh sách video yêu thích");
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/views/Bai4.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/Bai4.jsp").forward(request, response);
	}

}
