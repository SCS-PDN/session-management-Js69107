import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == "student1" && password == "pass1") {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                Cookie userCookie = new Cookie("username", username);
                userCookie.setMaxAge(60 * 60 * 24); 
                response.addCookie(userCookie);

                response.sendRedirect("DashboardServlet");

            } else {
                response.sendRedirect("login.html");
            }
        }
    }
}