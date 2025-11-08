

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChuNhat")
public class ChuNhat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChuNhat() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/ChuNhat/form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		request.setAttribute("dientich", a*b);
		request.setAttribute("chuvi", a+b+a+b);
		request.getRequestDispatcher("/ChuNhat/success.jsp").forward(request, response);
	}

}
