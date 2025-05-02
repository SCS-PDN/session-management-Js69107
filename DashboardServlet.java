import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
        }

        List<DashboardServlet.Course> courseList = new ArrayList<>();
        courseList.add(new DashboardServlet.Course("STA1013", "Introduction to Statistics", "Dr. Potter"));
        courseList.add(new DashboardServlet.Course("STA1023", "Sampling Technique", "Prof. Watson"));
        courseList.add(new DashboardServlet.Course("STA1033", "Advanced Probability Theory", "Mr. Johnson"));

        request.setAttribute("courses", courseList);

        List<DashboardServlet.Course> enrolledCourses = (List<DashboardServlet.Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
            session.setAttribute("enrolledCourses", enrolledCourses);
        }
        
        assert session != null;
        request.setAttribute("enrolledCourses", enrolledCourses);

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }


    public static class Course {
        private String id;
        private String name;
        private String instructor;

        public Course(String id, String name, String instructor) {
            this.id = id;
            this.name = name;
            this.instructor = instructor;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getInstructor() {
            return instructor;
        }
    }
}