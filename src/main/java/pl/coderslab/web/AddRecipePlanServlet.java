package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/plan/add")
public class AddRecipePlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Plan> planList = PlanDao.findAll();
        request.setAttribute("planList", planList);
        List<Recipe> recipeList = RecipeDao.findAll();
        request.setAttribute("recipeList", recipeList);
        List<DayName> dayList = DayNameDao.findAll();
        request.setAttribute("dayList", dayList);
        getServletContext().getRequestDispatcher("/add_recipe_plan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("select_plan"));
        System.out.println(request.getParameter("meal_number"));
        System.out.println(request.getParameter("meal_name"));
        System.out.println(request.getParameter("select_recipe"));
        System.out.println(request.getParameter("select_day"));
//                AdminDao.getLoginedAdmin(request.getSession()).getId());
        response.sendRedirect("/app/recipe/plan/add");
//        doGet(request, response);
    }
}
