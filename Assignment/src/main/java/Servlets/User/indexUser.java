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

import org.hibernate.Session;

import Dao.CategoryDao;
import Dao.FavoriteDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.CategoryDaoImpl;
import DaoImpl.FavoriteDaoImpl;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Favorite;
import Entity.Users;
import Entity.Video;

@WebServlet({"/indexUser","/indexUser/like/*","/indexUser/unlike/*","/indexUser/Share"})
public class indexUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public indexUser() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDao vdao = new VideoDaoImpl();
		UserDao udao = new UserDaoImpl();
		FavoriteDao fdao = new FavoriteDaoImpl();
		CategoryDao cdao = new CategoryDaoImpl();
		
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("id", id);	
		
		//Lấy user từ Login
		Users user = (Users) session.getAttribute("user");
		if(user != null) {
			String userid = user.getId();
			
			request.setAttribute("mess","Xin chào : "+user.getFullname());
			request.setAttribute("userid", userid);

			List<String> likedid = new ArrayList<>();
			for(Favorite favorite : fdao.findByUserId(userid)) {
				likedid.add(favorite.getVideo().getId());
			}
			request.setAttribute("likedIds", likedid);
		}
		

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
		request.getRequestDispatcher("/User/index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    UserDao udao = new UserDaoImpl();
	    VideoDao vdao = new VideoDaoImpl();
	    FavoriteDao fdao = new FavoriteDaoImpl();
	    HttpSession session = request.getSession();
	    
	    String path = request.getServletPath();
	    
	    Users UserTemp = (Users) session.getAttribute("user");
	    
	    if(UserTemp != null) {
	    	String userid = UserTemp.getId();
		    String videoid = request.getParameter("id");
		    String favoriteId = userid + videoid;
		    Date today = new Date();

		    // LIKE
		    if (path.equals("/indexUser/like")) {

		        Users user = udao.findById(userid);
		        Video video = vdao.findById(videoid);

		        Favorite f = new Favorite(favoriteId, user, video, today);
		        fdao.create(f);
		    }

		    // UNLIKE
		    else if (path.equals("/indexUser/unlike")) {

		        for (Favorite f : fdao.findByUserId(userid)) {
		            if (videoid.equals(f.getVideo().getId())) {
		                fdao.delete(f);
		                break;
		            }
		        }
		    }
		    if (path.equals("/indexUser/Share")) {
		    	response.sendRedirect(request.getContextPath()+"/Share?userid="+userid+"&id="+videoid);
		    	return;
		    }
		    if (path.equals("/indexUser/MyFavorites")) {
		    	response.sendRedirect(request.getContextPath()+"/MyFavorites");
		    	return;
		    }
		    if (path.equals("/indexUser/Login")) {
		    	session.setAttribute("user", null);
		    	response.sendRedirect(request.getContextPath()+"/indexUser");
		    	return;
		    }
	    

	    // 🔥 MUST HAVE: Load lại dữ liệu như doGet()
	    List<Video> allvideo = vdao.findall();
	    List<String> likedid = new ArrayList<>();

	    for (Favorite fav : fdao.findByUserId(userid)) {
	        likedid.add(fav.getVideo().getId());
	    }

	    Users user = udao.findById(userid);

	    request.setAttribute("mess", "Xin chào : " + user.getFullname());
	    request.setAttribute("userid", userid);
	    request.setAttribute("list", allvideo);
	    request.setAttribute("likedIds", likedid);
	    }

	    request.getRequestDispatcher("/User/index.jsp").forward(request, response);
	}


}
