package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static java.lang.Integer.parseInt;

@WebServlet("/app/recipe/add")
public class AddRecipeForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        request.setAttribute("loginedAdmin", loginedAdmin);

        request.getRequestDispatcher("/addrecipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        String description = request.getParameter("description");
        int preparationTime = 0;
        if (!"".equals(request.getParameter("preparationTime"))) {
            preparationTime = Integer.parseInt(request.getParameter("preparationTime"));
        }
        String preparation = request.getParameter("preparation");
        Recipe recipe = new Recipe(
                name,
                ingredients,
                description,
                preparationTime,
                preparation,
                AdminDao.getLoginedAdmin(request.getSession()).getId());
        if ((!"".equals(name)) && (!"".equals(ingredients)) &&
                (!"".equals(description)) && (!"".equals(preparation)) && (preparationTime != 0)) {
            recipe.setIngredients(recipe.getIngredients().trim().replaceAll("\\r", ", ").replaceAll("\\n", ""));
            RecipeDao.create(recipe);
            response.sendRedirect("/app/recipe/list");
        } else {
            request.setAttribute("recipe", recipe);
            request.getRequestDispatcher("/addrecipe.jsp").forward(request, response);
        }
    }
}