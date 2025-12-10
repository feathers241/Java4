package Servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

import Dao.UserDao;
import DaoImpl.UserDaoImpl;
import Entity.Users;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet({"/ForgotPassword","/ForgotPassword/otp"})
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/User/ForgotPassword.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		String email = request.getParameter("email");
		Users user = dao.findidemail(email);
		Random random = new Random();
		int a = random.nextInt(10);
		int b = random.nextInt(10);
		int c = random.nextInt(10);
		int d = random.nextInt(10);
		String randomNum = a+""+b+""+c+""+d;
		request.setAttribute("randomNum", randomNum);
		
		if(user!=null) {
			String subject = "Share video từ thằng bạn chí cốt (Xương cốt) " + user.getFullname();
		    String message = "Người dùng " + user.getFullname() +" đây là mã khôi phục mật khẩu của bạn : ";
		    
		    try {
//		        MailUtils.sendEmail("notAdmin",email, subject, message);
		        System.out.println("Gửi email thành công!");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("Gửi email thất bại: ");
		    }
		    
		    String path = request.getServletPath();
		    if(path.contains("otp")) {
		    	String check = request.getParameter("otp");
		    	if(check.equals(randomNum)) {
		    		response.sendRedirect(request.getContextPath()+"/ChangePassword&iduser="+user.getId());
		    	}
		    }
		}else {
			request.setAttribute("mess", "Email sai hoặc chưa được đăng kí");
		}
		
		request.getRequestDispatcher("/User/ForgotPassword.jsp").forward(request, response);
	}

}
