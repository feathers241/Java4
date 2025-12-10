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
import Dao.HistoryDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.FavoriteDaoImpl;
import DaoImpl.HistoryDaoImpl;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Favorite;
import Entity.History;
import Entity.Users;
import Entity.Video;

/**
 * Servlet implementation class CardDetails
 */
@WebServlet({"/CardDetails/*","/CardDetails/Share"})
public class CardDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDao vdao = new VideoDaoImpl();
		FavoriteDao fdao = new FavoriteDaoImpl();
		HistoryDao hdao = new HistoryDaoImpl();
		UserDao udao = new UserDaoImpl();
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		List<Video> allvideo = vdao.findVideosByPage(1,5);
		request.setAttribute("list", allvideo);
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if(id != null) {
			request.setAttribute("main", vdao.findById(id));
			Video video = vdao.findById(id);
			video.setViews(video.getViews()+1);
			vdao.update(video);
		}
		if(user != null) {
			String userid = user.getId();

			request.setAttribute("userid", userid);
			
			//Thêm vào lịch sử
			Users newuser = udao.findById(userid);
			Video newvideo = vdao.findById(id);
			History history = new History(newuser,newvideo);

			List<String> likedid = new ArrayList<>();
			for(Favorite favorite : fdao.findByUserId(userid)) {
				likedid.add(favorite.getVideo().getId());
			}

			request.setAttribute("likedIds", likedid);
		}
		
		request.getRequestDispatcher("/User/CardDetails.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 UserDao udao = new UserDaoImpl();
		    VideoDao vdao = new VideoDaoImpl();
		    FavoriteDao fdao = new FavoriteDaoImpl();

		    String path = request.getPathInfo();
		    String userid = request.getParameter("userid");
		    String videoid = request.getParameter("id");
		    String favoriteId = userid + videoid;
		    Date today = new Date();

		    // LIKE
		    if ("/like".equals(path)) {

		        Users user = udao.findById(userid);
		        Video video = vdao.findById(videoid);

		        Favorite f = new Favorite(favoriteId, user, video, today);
		        fdao.create(f);
		    }

		    // UNLIKE
		    else if ("/unlike".equals(path)) {
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

		    if(videoid != null) {
				request.setAttribute("main", vdao.findById(videoid));
			}
		    List<Video> allvideo = vdao.findall();
			List<String> likedid = new ArrayList<>();
			for(Favorite favorite : fdao.findByUserId(userid)) {
				likedid.add(favorite.getVideo().getId());
			}
			request.setAttribute("list", allvideo);
			request.setAttribute("likedIds", likedid);

		    request.setAttribute("userid", userid);
			request.getRequestDispatcher("/User/CardDetails.jsp").forward(request, response);

	}

}
