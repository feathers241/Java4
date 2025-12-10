package Filter;

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

import Entity.Users;

@WebFilter({"/indexUser","/indexUser/*","/CardDetails/*"})
public class Auther extends HttpFilter implements Filter {
       
    public Auther() {
        super();
    }


	public void destroy() {}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse rs = (HttpServletResponse) response;
		HttpSession session = rq.getSession();
		Users user = (Users) session.getAttribute("user");
		
		String method = rq.getMethod();

		if(method.equalsIgnoreCase("GET")) {
	        chain.doFilter(request, response);
	        return;
	    }

	    
	    
		if(user == null) {
			session.setAttribute("secureURL", rq.getRequestURI());
			rs.sendRedirect(rq.getContextPath()+"/Login");
		}else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
