package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Utils.CookieUtils;
import Utils.RRSharer;

@WebServlet("/TestCookie")
public class TestCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TestCookie() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RRSharer.add(request, response);

        Cookie cUser = CookieUtils.get("getUsername");
        Cookie cPass = CookieUtils.get("getPassword");
        Cookie cRemember = CookieUtils.get("getRemember");

        if (cUser != null && cPass != null && cRemember != null) {

            if ("true".equals(cRemember.getValue())) {
                request.setAttribute("getUsername", cUser.getValue());
                request.setAttribute("getPassword", cPass.getValue());
                request.setAttribute("getRemember", "checked");
            }else {
                request.setAttribute("getRemember", "");
            }
        }

        request.getRequestDispatcher("/views/TextCookie.jsp").forward(request, response);
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RRSharer.add(request, response);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		boolean rememeber = Boolean.parseBoolean(request.getParameter("remember"));
		
		if(username.equals("user") && password.equals("123") ) {
			if(rememeber) {
				request.setAttribute("mess", "Đăng nhập thành công và có remember ");
				Cookie cookieUsername = CookieUtils.add("username", username, 1);
				Cookie cookiePassword = CookieUtils.add("password", password, 1);
				Cookie cookieRemember = CookieUtils.add("remember", request.getParameter("remember"), 1);
				
				response.addCookie(cookieUsername);
				response.addCookie(cookiePassword);
				response.addCookie(cookieRemember);
				
				response.sendRedirect(request.getContextPath() + "/Logout");
				return;
			}if(!rememeber) {
				request.setAttribute("mess", "Đăng nhập thành công và không có remember ");
				Cookie cookieUsername = CookieUtils.add("username", username, 1);
				Cookie cookiePassword = CookieUtils.add("password", password, 1);
				Cookie cookieRemember = CookieUtils.add("remember", request.getParameter("remember"), 0);

				response.addCookie(cookieUsername);
				response.addCookie(cookiePassword);
				response.addCookie(cookieRemember);
				response.sendRedirect(request.getContextPath() + "/Logout");
				return;
			}
		}
		request.getRequestDispatcher("/views/TextCookie.jsp").forward(request, response);
	}
}
