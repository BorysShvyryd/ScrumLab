package pl.coderslab.web;

import pl.coderslab.dao.*;
import pl.coderslab.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/plan/add")
public class AddRecipePlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin loginedAdmin = AdminDao.getLoginedAdmin(session);
        request.setAttribute("loginedAdmin", loginedAdmin);
        List<Plan> planList = PlanDao.findAllPlanByAdminId(loginedAdmin.getId());
        request.setAttribute("planList", planList);
        List<Recipe> recipeList = RecipeDao.findAllRecipeByAdminId(loginedAdmin.getId());
        request.setAttribute("recipeList", recipeList);
        List<DayName> dayList = DayNameDao.findAll();
        request.setAttribute("dayList", dayList);
        getServletContext().getRequestDispatcher("/add_recipe_plan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipePlanDao.saveRecipePlanToDB(new RecipePlan(
                Integer.parseInt(request.getParameter("select_recipe")),
                request.getParameter("meal_name"),
                Integer.parseInt(request.getParameter("meal_number")),
                Integer.parseInt(request.getParameter("select_day")),
                Integer.parseInt(request.getParameter("select_plan"))));

        response.sendRedirect("/app/plan/details?plan_id=" + request.getParameter("select_plan"));
    }
}
