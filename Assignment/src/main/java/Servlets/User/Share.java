package Servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import Dao.ShareDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.ShareDaoImpl;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Users;
import Entity.Video;	
/**
 * Servlet implementation class Share
 */
@WebServlet("/Share/*")
public class Share extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @param today 
     * @param emailto 
     * @param video 
     * @param user 
     * @param shareid 
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		request.setAttribute("userid", userid);
		request.getRequestDispatcher("/User/Share.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao udao = new UserDaoImpl();
		VideoDao vdao = new VideoDaoImpl();
		ShareDao sdao = new ShareDaoImpl();
		
		String userid = request.getParameter("userid");
		String videoid = request.getParameter("id");
		String emailto = request.getParameter("email");
		
		Users user = udao.findById(userid);
		Video video = vdao.findById(videoid);
		
		 String subject = "Share video từ thằng bạn chí cốt (Xương cốt) " + user.getFullname();
		    String message = "Người dùng " + user.getFullname() + 
		                     " đã chia sẻ video \"" + video.getTitle() +
		                     "\" tới bạn.\nLink video: http://localhost:8080/Assignment/DetailFirst?id="+video.getId() ;
		    
		   Random random = new Random();
		   int a = random.nextInt(10);
		   int b = random.nextInt(10);
		   String shareid = user.getId() + a + video.getId() +b;
		   Date today = new Date();
		    try {
		        MailUtils.sendEmail(user.getFullname(),emailto, subject, message);
		        System.out.println("Gửi email thành công!");
		        Entity.Share shared = new Entity.Share(shareid,user,video,emailto,today);
		        sdao.create(shared);
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("Gửi email thất bại: ");
		    }
		request.getRequestDispatcher("/User/Share.jsp").forward(request, response);
	}
// Share{id='7', userId='user03', videoId='vid002', emails='null', shareDate=2024-05-20}
}
