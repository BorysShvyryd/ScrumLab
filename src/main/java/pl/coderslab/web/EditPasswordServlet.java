package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/edit/password")
public class EditPasswordServlet extends HttpServlet {
    boolean verifyPassword = true;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        request.setAttribute("loginedAdmin", loginedAdmin);
        request.setAttribute("werifyPassword", verifyPassword);

        request.getRequestDispatcher("/edit_password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        if (pass1.equals(pass2)) {
            loginedAdmin.setPassword(AdminDao.hashPassword(pass1));
            AdminDao.update(loginedAdmin);
            verifyPassword = true;
            response.sendRedirect("/app/dashboard");
        } else {
            verifyPassword = false;
            response.sendRedirect("/app/edit/password");
        }

    }
}
