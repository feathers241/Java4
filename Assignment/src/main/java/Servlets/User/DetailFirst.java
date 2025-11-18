package Servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import Dao.VideoDao;
import DaoImpl.VideoDaoImpl;
import Entity.Video;

/**
 * Servlet implementation class DetailFirst
 */
@WebServlet("/DetailFirst")
public class DetailFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailFirst() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoDao vdao = new VideoDaoImpl();
		request.setAttribute("list", vdao.findall());
		
		
		String id = request.getParameter("id");
		if(id != null) {
			HttpSession session = request.getSession();
			request.setAttribute("main", vdao.findById(id));
			Video video = vdao.findById(id);
			video.setViews(video.getViews()+1);
			vdao.update(video);
		}
		request.getRequestDispatcher("/User/DetailFirst.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
