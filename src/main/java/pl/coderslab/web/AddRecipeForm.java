package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/app/recipe/add")
public class AddRecipeForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/addrecipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String timePreparation = request.getParameter("timePreparation");
        String typeOf = request.getParameter("typeOf");
        String ingredients = request.getParameter("ingredients");

        Recipe recipe = new Recipe();
        RecipeDao recipeDao = new RecipeDao();
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setPreparation_time(Integer.parseInt(timePreparation));
        recipe.setPreparation(typeOf);
        recipe.setIngredients(ingredients);
        try {
            recipeDao.create(recipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/addrecipe.jsp").forward(request, response);
    }
}
