package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("userId", userId);
        request.setAttribute("numberRecipe", RecipeDao.getNumberRecipeByAdmin(userId));
        request.setAttribute("numberPlan", PlanDao.getNumberPlanByAdmin(userId));

//request.setAttribute("lastPlan", AdminDao.LastPlan(userId));

        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
