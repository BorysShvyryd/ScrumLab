package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorMessage = "";
        //AdminDao admin =
        int adminId = AdminDao.verificationOfAdminData(request.getParameter("email"), request.getParameter("password"));
        //  if (admin != null) {
        if (adminId > 0) {
            HttpSession session = request.getSession();
            AdminDao user = new AdminDao();
            AdminDao.storeLoginedUser(session, user);
            session.setMaxInactiveInterval(6*60*60);

            response.sendRedirect("/dashboard");
        } else {
            errorMessage = "Invalid Email or Password";
            response.sendRedirect("/login");
        }
    }
}
