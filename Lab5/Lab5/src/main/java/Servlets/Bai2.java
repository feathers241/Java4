package Servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class Bai2 implements ServletContextListener, HttpSessionListener {

    public Bai2() {
        // TODO Auto-generated constructor stub
    }
    private final String FILE_PATH = "D:/visitors.txt"; // đường dẫn file lưu số đếm


    public void sessionCreated(HttpSessionEvent se)  { 
    	 ServletContext application = se.getSession().getServletContext();

         synchronized (application) {
             Integer count = (Integer) application.getAttribute("visitors");
             if (count == null) count = 0;
             count++;
             application.setAttribute("visitors", count);
         }
         System.out.println("New session created. Total visitors = " 
                             + application.getAttribute("visitors"));
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();

        int visitors = 0;

        // Đọc số đếm từ file
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                if (line != null) visitors = Integer.parseInt(line.trim());
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        application.setAttribute("visitors", visitors);
        System.out.println("Application started, visitors = " + visitors);
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();
        int visitors = (Integer) application.getAttribute("visitors");

        // Ghi lại file khi tắt server
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            writer.write(String.valueOf(visitors));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Application stopped. Saved visitors = " + visitors);
    }
	
}
