package Servlets.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import Entity.Share;
import Entity.Users;
import Entity.Video;

/**
 * Servlet implementation class ReportManager
 */
@WebServlet({"/ReportManager"})
public class ReportManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShareDao sdao = new ShareDaoImpl();
		UserDao udao = new UserDaoImpl();
		VideoDao vdao = new VideoDaoImpl();
		FavoriteDao fdao = new FavoriteDaoImpl();
		
		
		// Lấy adminid từ indexAdmin
		String adminid = request.getParameter("adminid");
		
		//Favorites
		List<List<String>> info = new ArrayList<>();
		for(Object[] object : sdao.shareinfo()) {
			List<String> temp = new ArrayList<>();
			String title = object[0].toString();
			String sharecount = object[1].toString();
			String firstshare = object[2].toString();
			String lastshare = object[3].toString();
			temp.add(title);
			temp.add(sharecount);
			temp.add(firstshare);
			temp.add(lastshare);
			info.add(temp);
		}
		request.setAttribute("shareinfo", info);
		
		///   //Favorite Users
		List<Video> ListVideo = vdao.findall();
		HashMap<String, String> idtitle = new HashMap<>();
		List<String> title = new ArrayList<>();
		for(Video v : ListVideo) {
			idtitle.put(v.getId(), v.getTitle());
			title.add(v.getTitle());
		}
		request.setAttribute("alltitle", title);
		
		//Lấy title và id title
		String getTitle = request.getParameter("getTitle");
		String getVideoId = null;
		if(getTitle!=null) {
			for(String key : idtitle.keySet()) {
				if(idtitle.get(key).equals(getTitle)) {
					getVideoId = key;
				}
			}
		}
		
		List<List<Object>> storage = new ArrayList<>();
		for(Favorite f : fdao.findall()) {
			if(f.getVideo().getId().equals(getVideoId)) {
				List<Object> items = new ArrayList<>();
				items.add(f.getUser().getId());
				items.add(f.getUser().getFullname());
				items.add(f.getUser().getEmail());
				items.add(f.getLikeDate());
				storage.add(items);
			}
		}
		request.setAttribute("storage", storage);
		
		//Shared Friends
		List<List<Object>> SHARE = new ArrayList<>();
		for(Share s : sdao.findall()) {
			List<Object> share = new ArrayList<>();
			share.add(s.getUser().getFullname());
			share.add(s.getUser().getEmail());
			share.add(s.getEmails());
			share.add(s.getShareDate());
			SHARE.add(share);
		}
		request.setAttribute("share", SHARE);
		
		//tab
		String tab = request.getParameter("tab");
		if (tab == null) tab = "fav"; // tab mặc định
		request.setAttribute("tab", tab);

		request.getRequestDispatcher("/Admin/ReportManager.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
