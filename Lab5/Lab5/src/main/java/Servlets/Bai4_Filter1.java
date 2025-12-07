package Servlets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class Bai4_Filter1 extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Bai4_Filter1() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		HttpServletRequest rq = (HttpServletRequest) request; 
		HttpSession session = rq.getSession();
		
        chain.doFilter(request, response);
        
        Object a = session.getAttribute("a");
    	
        if(a != null) {
        	System.out.println("Filter1 lấy a ở servlet = "+a);
        	session.setAttribute("b", a);  	
        }else {
        	System.out.println("Méo được duyệt !");
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
