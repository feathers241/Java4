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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;

/**
 * Servlet Filter implementation class Bai3
 */
@WebFilter("/adf")
public class Bai3 extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Bai3() {
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
		 // Thiết lập mã hóa chung
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        
        System.out.println("AppFilter đã chạy trước Servlet!");
        
        chain.doFilter(request, response);
        //Phần phía dưới dòng này là filter ( để kiểm tra điều kiện, chuyển tới trang khác )
        
        HttpServletRequest rq = (HttpServletRequest) request; 
        HttpSession session = rq.getSession();
        Object a = session.getAttribute("a");
        String uri = rq.getRequestURI();
        if (uri.endsWith("/Bai")) {
        	if(a != null) {
                // Cho request đi tiếp
                System.out.println("Đáp án là : " + a );

            }else {
            	System.out.println("Méo được duyệt");
            }
        }

        System.out.println("AppFilter đã chạy sau Servlet!");

        
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
