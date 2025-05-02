import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseId = request.getParameter("courseId");
        
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
        }

        List<DashboardServlet.Course> courseList = new ArrayList<>();
        courseList.add(new DashboardServlet.Course("STA1013", "Introduction to Statistics", "Dr. Potter"));
        courseList.add(new DashboardServlet.Course("STA1023", "Sampling Technique", "Prof. Watson"));
        courseList.add(new DashboardServlet.Course("STA1033", "Advanced Probability Theory", "Mr. Johnson"));

        List<DashboardServlet.Course> enrolledCourses = (List<DashboardServlet.Course>) session.getAttribute("enrolledCourses");
        for (DashboardServlet.Course course : courseList) {
            if (course.getId().equals(courseId)) {
                enrolledCourses.add(course);
            }
        }
        session.setAttribute("enrolledCourses", enrolledCourses);

        response.sendRedirect("DashboardServlet");
    }
}