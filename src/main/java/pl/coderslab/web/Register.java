package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String[] passwords = request.getParameterValues("password");

        Admin admin = new Admin();
        AdminDao adminDao = new AdminDao();
        admin.setFirstName(name);
        admin.setLastName(surname);
        admin.setEmail(email);
        admin.setPassword(passwords[0]);
        adminDao.create(admin);

        response.sendRedirect("/login");

/*        response.getWriter().append(name).append("\n");
        response.getWriter().append(surname).append("\n");
        response.getWriter().append(email).append("\n");
        response.getWriter().append(String.valueOf(passwords));*/
    }
}
