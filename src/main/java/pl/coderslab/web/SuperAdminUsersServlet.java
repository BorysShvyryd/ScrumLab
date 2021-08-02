package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/super/admin/users")
public class SuperAdminUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        request.setAttribute("loginedAdmin", loginedAdmin);
        if (loginedAdmin.getSuperadmin() != 1) {
            response.sendRedirect("/app/dashboard");
        } else {
            List<Admin> allUsers = AdminDao.findAll();
            request.setAttribute("allUsers", allUsers);
            request.getRequestDispatcher("/super_admin_users.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String blok = request.getParameter("block");
        String unblok = request.getParameter("unblock");
        if (blok != null) {
            AdminDao.blokUnblokUser(Integer.parseInt(blok), 0);
        }
        if (unblok != null) {
            AdminDao.blokUnblokUser(Integer.parseInt(unblok), 1);
        }
        response.sendRedirect("/app/super/admin/users");
    }
}
