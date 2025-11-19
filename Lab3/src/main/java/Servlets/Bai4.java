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
import Dao.ShareDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.FavoriteDaoImpl;
import DaoImpl.ShareDaoImpl;
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


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ShareDao sdao = new ShareDaoImpl();
    	FavoriteDao fdao = new FavoriteDaoImpl();
    	
    	List<List<Object>> box = new ArrayList<>();
    	for(Favorite f : fdao.findall()) {
    		List<Object> item = new ArrayList<>();
    		item.add(f.getVideo().getTitle());
    		item.add(f.getUser().getFullname());
    		item.add(f.getLikeDate());
    		box.add(item);
    	}
    	request.setAttribute("list", box);
		request.getRequestDispatcher("/views/Bai4.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/Bai4.jsp").forward(request, response);
	}

}
