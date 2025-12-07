package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Servlet implementation class Bai4
 */
@WebServlet("/Bai4")
public class Bai4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	int count = 0;
	Path path = Paths.get("d:/count.txt");
	
    public Bai4() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
        try {
            count = Integer.parseInt(Files.readAllLines(path).get(0));
        } catch (Exception e) {
            count = 0; 
            e.printStackTrace();
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        count++;
        req.setAttribute("count", count);

        req.getRequestDispatcher("/views/Bai4.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        try {
            Files.write(path, String.valueOf(count).getBytes(), StandardOpenOption.WRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/Bai4.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
