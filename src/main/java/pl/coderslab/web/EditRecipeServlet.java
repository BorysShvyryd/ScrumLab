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

@WebServlet("/app/recipe/edit")
public class EditRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        request.setAttribute("loginedAdmin", loginedAdmin);
        request.setAttribute("adminRecipe", RecipeDao.read(Integer.parseInt(request.getParameter("id"))));

        request.getRequestDispatcher("/edit_recipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Recipe recipe = new Recipe(request.getParameter("name")
                , request.getParameter("ingredients").trim().replaceAll("\\r",", ").replaceAll("\\n", "")
                , request.getParameter("description")
                , Integer.parseInt(request.getParameter("preparation_time"))
                , request.getParameter("preparation")
                , AdminDao.getLoginedAdmin(request.getSession()).getId()
                , Integer.parseInt(request.getParameter("id")));
        RecipeDao.update(recipe);
        response.sendRedirect("/app/recipe/list");
    }
}
