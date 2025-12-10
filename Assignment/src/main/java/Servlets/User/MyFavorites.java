package Servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
 * Servlet implementation class MyFavorites
 */
@WebServlet({"/MyFavorites","/MyFavorites/*","/MyFavorites/Share"})
public class MyFavorites extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyFavorites() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FavoriteDao fdao = new FavoriteDaoImpl();
		HttpSession session = request.getSession();
		
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			String id = user.getId();
			List<Video> temp = new ArrayList<>();
			for(Favorite favo : fdao.findByUserId(id)) {
				temp.add(favo.getVideo());
			}
			request.setAttribute("list", temp);
			request.setAttribute("userid",id);
		}

		request.getRequestDispatcher("/User/MyFavorite.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao udao = new UserDaoImpl();
        VideoDao vdao = new VideoDaoImpl();
        FavoriteDao fdao = new FavoriteDaoImpl();
        
        String path = request.getPathInfo(); // <--- FIX
        String userid = request.getParameter("userid");
        String videoid = request.getParameter("id");
        String favoriteId = userid + videoid;
        Date today = new Date();

        // UNLIKE
        if ("/unlike".equals(path)) {
            for (Favorite f : fdao.findByUserId(userid)) {
                if (videoid.equals(f.getVideo().getId())) {
                    fdao.delete(f);
                    break;
                }
            }
        }
        String path2 = request.getServletPath();
        if(path2.contains("Share")) {
        	response.sendRedirect(request.getContextPath()+"/Share?userid="+userid+"&id="+videoid);
	    	return;
        }

        // LOAD LIST AGAIN
        List<Video> temp = new ArrayList<>();
        for (Favorite favo : fdao.findByUserId(userid)) {  // <--- FIX
            temp.add(favo.getVideo());
        }

        request.setAttribute("list", temp);
        request.setAttribute("userid", userid);
        request.getRequestDispatcher("/User/MyFavorite.jsp").forward(request, response);
    }

}
