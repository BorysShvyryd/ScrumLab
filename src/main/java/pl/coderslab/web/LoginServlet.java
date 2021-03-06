package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

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

        Admin admin = AdminDao.verificationOfAdminData(request.getParameter("email"), request.getParameter("password"));
        if (admin != null) {
            HttpSession session = request.getSession();
            AdminDao.storeLoginedUser(session, admin);
            session.setMaxInactiveInterval(6*60*60);
            response.sendRedirect("/app/dashboard");
        } else {
            errorMessage = "Invalid Email or Password";

            response.sendRedirect("/login");
        }
    }
}
