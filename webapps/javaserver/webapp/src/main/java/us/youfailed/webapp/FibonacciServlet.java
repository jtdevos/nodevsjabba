package us.youfailed.webapp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by jim on 9/28/16.
 */
@WebServlet(name = "FibonacciAction", urlPatterns = "/fib")
public class FibonacciServlet extends HttpServlet {
    public static final int MAX_INPUT_VAL = 42;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String[] nums = request.getParameterValues("i");
        int i = 0;
        if(nums == null) {
            response.sendError(500, "no value supplied");
        } else if(nums.length < 1) {
            response.sendError(500, "no value supplied");
            i = 0;
        } else {
            i = Integer.parseInt(nums[0]);
            if(i > MAX_INPUT_VAL) {
                response.sendError(400, "Woah buddy. We can't handle big values like that");
            } else {
                int f =  fib(i);
                String fs = Integer.toString(f);
                response.getOutputStream().write(fs.getBytes("UTF8"));
            }
        }
    }




    /**
     * Simplistic implementation of fibbonacci sequence
     * @param i
     * @return
     */
    public int fib(int i) {
        if(i == 1) return 1;
        if(i == 0) return 1;
        return fib(i - 1) + fib(i - 2);
    }

}
