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
        if (email.matches("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}")) {
            admin.setEmail(email);
        } else {
            admin.setEmail("");
        }
        admin.setPassword(passwords[0]);

        if (passwords[0].equals(passwords[1]) &&
                passwords[0].length() > 0 &&
                name.length() > 0 &&
                surname.length() > 0 &&
                email.length() > 0) {
            adminDao.create(admin);
            response.sendRedirect("/login");
        } else {
            request.setAttribute("name", admin.getFirstName());
            request.setAttribute("surname", admin.getLastName());
            request.setAttribute("email", admin.getEmail());
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }
    }
}
