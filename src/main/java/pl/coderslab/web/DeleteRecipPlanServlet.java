package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/recipe/plan/delete")
public class DeleteRecipPlanServlet extends HttpServlet {
    String deleteRecipeIdInPlan = "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("plan_id", request.getParameter("plan_id"));
        deleteRecipeIdInPlan = request.getParameter("id");
        request.setAttribute("recipe_name", request.getParameter("name"));
        request.setAttribute("plan_name", PlanDao.read(Integer.parseInt(request.getParameter("plan_id"))).getName());
        request.getRequestDispatcher("/delete_recipe_plan_question.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipePlanDao.deleteRecipePlanInDB(Integer.parseInt(deleteRecipeIdInPlan));
        response.sendRedirect("/app/plan/details?plan_id=" + request.getParameter("plan_id"));

    }
}
