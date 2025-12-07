package Servlets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class Bai4_Filter2 extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Bai4_Filter2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        

		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
		HttpServletRequest rq = (HttpServletRequest) request; 
		HttpSession session = rq.getSession();
		
        chain.doFilter(request, response);
			
		Object b = session.getAttribute("b");
        System.out.println("b = " + b + ", Nice to meet you !");
        
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
