package us.youfailed.webapp;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created by JAMES on 9/24/2016.
 */
@WebServlet(name = "ListingAction", urlPatterns = "/listing")
public class ListngServlet extends HttpServlet {

    ServletOutputStream out;
    HttpServletRequest request;
    HttpServletResponse response;
    Path filesPath;
    File filesDir;



    public ListngServlet() {
        String strFilesDir = System.getenv("OUTFILESDIR");

        if(strFilesDir == null) {
            strFilesDir = System.getProperty("java.io.tmpdir");
            System.err.println(String.format("OUTFILESDIR not defined. Using this instead: %s", strFilesDir));
        }
        filesPath = Paths.get(strFilesDir);
        filesDir = Paths.get(strFilesDir).toFile();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletOutputStream out = response.getOutputStream();
        this.out = out;
        this.request = request;
        this.response = response;
        putsl("listing goes here");
        if(!filesDir.exists()) {
            putsl("no directory found");
        } else {
            putsl("-- directory contents --");
            sendListing();
        }
    }


    private void sendListing() throws IOException {
        DirectoryStream<Path> paths = Files.newDirectoryStream(filesPath);
        paths.forEach( path -> putsl(path.toFile().getAbsolutePath()));


    }


    private void puts(String format, Object... args)  {
        try {
            out.write(String.format(format, args).getBytes("UTF8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putsl(String format, Object...args) {
        puts(format, args);
        puts("\n");
    }

}
