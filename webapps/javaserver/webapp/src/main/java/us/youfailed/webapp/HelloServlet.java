package us.youfailed.webapp;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by JAMES on 9/24/2016.
 */
@WebServlet(name = "MyServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        ServletOutputStream out = response.getOutputStream();
        out.write("hello there".getBytes("UTF8"));
    }
}
