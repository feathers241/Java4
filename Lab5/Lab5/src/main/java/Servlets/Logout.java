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

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RRSharer.add(request, response);

        Cookie cookieUsername = CookieUtils.get("username");
        Cookie cookiePassword = CookieUtils.get("password");

        request.setAttribute("username", cookieUsername.getValue());
        request.setAttribute("password", cookiePassword.getValue());

        request.getRequestDispatcher("/views/Logout.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RRSharer.add(request, response);

        Cookie cookieUsername = CookieUtils.get("username");
        Cookie cookiePassword = CookieUtils.get("password");
        Cookie cookieRemember = CookieUtils.get("remember");

        
        if(cookieRemember != null) {
        	// lưu lại giá trị để TestCookie lấy
            response.addCookie(CookieUtils.add("getUsername", cookieUsername.getValue(), 1));
            response.addCookie(CookieUtils.add("getPassword", cookiePassword.getValue(), 1));
        	response.addCookie(CookieUtils.add("getRemember", cookieRemember.getValue(), 1));
        	

            // xóa cookie gốc
            cookieUsername.setMaxAge(0);
            cookiePassword.setMaxAge(0);
            cookieRemember.setMaxAge(0);

            response.addCookie(cookieUsername);
            response.addCookie(cookiePassword);
            response.addCookie(cookieRemember);
        }else {
        	// lưu lại giá trị để TestCookie lấy
            response.addCookie(CookieUtils.add("getUsername", cookieUsername.getValue(), 1));
            response.addCookie(CookieUtils.add("getPassword", cookiePassword.getValue(), 1));
        	response.addCookie(CookieUtils.add("getRemember", "false", 1));
        	

            // xóa cookie gốc
            cookieUsername.setMaxAge(0);
            cookiePassword.setMaxAge(0);

            response.addCookie(cookieUsername);
            response.addCookie(cookiePassword);
        }
        


        response.sendRedirect(request.getContextPath() + "/TestCookie");
    }


}
