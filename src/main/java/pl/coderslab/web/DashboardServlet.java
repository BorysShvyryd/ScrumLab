package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        request.setAttribute("loginedAdmin", loginedAdmin);
        request.setAttribute("numberRecipe", RecipeDao.getNumberRecipeByAdmin(loginedAdmin.getId()));
        request.setAttribute("numberPlan", PlanDao.getNumberPlanByAdmin(loginedAdmin.getId()));
        Plan lastPlan = PlanDao.lastPlan(loginedAdmin.getId());
        request.setAttribute("lastPlan", lastPlan);
        List<List<String>> lastPlanDetails = PlanDao.lastPlanDetails(loginedAdmin.getId());
        request.setAttribute("lastPlanDetails", lastPlanDetails);

        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
