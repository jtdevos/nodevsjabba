package us.youfailed.webapp;

import launch.Config;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Servlet that serves static file,  e.g. /serve-file?name=foo.html
 */
@WebServlet(name = "ServeFileAction", urlPatterns = "/serve-file")
public class ServeFileServlet extends HttpServlet{

    Config config = Config.get();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String[] names = request.getParameterMap().get("name");
        if(names == null) response.sendError(404, "no filename supplied");
        if(names.length < 1) response.sendError(404, "no filename supplied");
        File file = config.getFilesPath().resolve(names[0]).toFile();
        if(!file.exists()) response.sendError( 404, "file not found");

        Files.copy(config.getFilesPath().resolve(names[0]), response.getOutputStream());
    }
}
