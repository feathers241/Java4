package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Dao.FavoriteDao;
import Dao.ShareDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.FavoriteDaoImpl;
import DaoImpl.ShareDaoImpl;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Users;
import Entity.Video;

/**
 * Servlet implementation class Bai1
 */
@WebServlet({"/Bai1","/Bai1/findidemail","/Bai1/findkeyword"})
public class Bai1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bai1() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FavoriteDao fdao = new FavoriteDaoImpl();
		VideoDao vdao = new VideoDaoImpl();
		ShareDao sdao = new ShareDaoImpl();
		
		
		List<Object[]> ob = fdao.tenmostliked();
		List<Video> last = new ArrayList();
		for(int i = 0 ; i < ob.size() ; i++) {
			Object[] list = ob.get(i);
			Video video = vdao.findById((String) list[0]);
			last.add(video);
		}
		request.setAttribute("like", last);
		
		List<Video> notlike = new ArrayList<>();
		for(Video video : vdao.notlike()) {
			notlike.add(video);
		}
		request.setAttribute("notlike", notlike);
		
		List<Object[]> whate = vdao.sharein2024();

		List<List<String>> share2024 = new ArrayList<>();

		for (Object[] row : whate) {
		    Video video = vdao.findById((String) row[0]);
		    String date = row[1].toString();
		    List<String> info = new ArrayList<>();
		    info.add(video.getId());
		    info.add(video.getTitle());
		    info.add(video.getPoster());
		    info.add(String.valueOf(video.getViews()));
		    info.add(video.getDescription());
		    info.add(date);
		    share2024.add(info);
		}

		request.setAttribute("share2024", share2024);
		

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
		request.getRequestDispatcher("/views/Bai1.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		VideoDao vdao = new VideoDaoImpl();
		
		String path = request.getServletPath();
		
		String value = request.getParameter("id");
		String keyword = request.getParameter("keyword");
		
		if(path.contains("findidemail") && value != null) {
			Users user = dao.findidemail(value);
			List list = new ArrayList();
			list.add(user);
			request.setAttribute("list", list);
		}
		if(path.contains("keyword") ) {
			List list = new ArrayList();
			for(Video video : vdao.findall()) {
				if(video.getTitle().contains(keyword)) {
					list.add(video);
				}
			}
			request.setAttribute("video", list);
		}
		request.getRequestDispatcher("/views/Bai1.jsp").forward(request, response);
	}

}
