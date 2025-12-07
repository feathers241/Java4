package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Dao.FavoriteDao;
import Dao.ShareDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.FavoriteDaoImpl;
import DaoImpl.ShareDaoImpl;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Users;
import Entity.Video;

/**
 * Servlet implementation class Bai1
 */
@WebServlet({"/Bai1"})
public class Bai1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bai1() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = """
	            {
	                "manv": "TeoNV",
	                "hoTen":"Nguyễn Văn Tèo",
	                "gioiTinh": true,
	                "luong": 950.5
	            }
	        """;

	        response.setContentType("application/json;charset=utf-8");
	        response.getWriter().print(json);
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
