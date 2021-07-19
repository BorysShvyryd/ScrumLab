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

        // hashPassword  - metoda haszowania paroli

        String hashPass = "hashPassword(request.getParameter(\"password\"))";

        if (AdminDao.verificationOfAdminData(request.getParameter("email"), hashPass)) {
            response.sendRedirect("");
        } else {
            response.sendRedirect("/login");
        }
    }
}
