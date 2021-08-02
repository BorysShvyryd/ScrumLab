package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.Console;
import java.io.IOException;

@WebServlet("/app/edit/user/data")
public class EditUserDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        request.setAttribute("loginedAdmin", loginedAdmin);

        request.getRequestDispatcher("/edit_user_data.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        loginedAdmin.setFirstName(request.getParameter("firstName"));
        loginedAdmin.setLastName(request.getParameter("lastName"));
        loginedAdmin.setEmail(request.getParameter("email"));
        AdminDao.update(loginedAdmin);
        response.sendRedirect("/app/dashboard");
    }
}
