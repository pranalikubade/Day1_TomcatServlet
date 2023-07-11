import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlets")
public class LoginServlets extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("password");

        if (isValidUsername(username) && isValidPassword(password) && username.equals("Admin") && password.equals("Pranali@123")) {
            response.sendRedirect("LoginSuccess.jsp");
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Incorrect Credentials</font>");
            requestDispatcher.include(request, response);
        }
    }

    private boolean isValidUsername(String username) {
        return username.matches("^[A-Z][a-zA-Z]{2,}$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
