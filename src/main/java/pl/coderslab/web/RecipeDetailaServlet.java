package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/recipe/details")
public class RecipeDetailaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin loginedAdmin = AdminDao.getLoginedAdmin(session);
        request.setAttribute("loginedAdmin", loginedAdmin);

        int recipeId = Integer.parseInt(request.getParameter("id"));

        Recipe recipe = RecipeDao.read(recipeId);
        request.setAttribute("recipe", recipe);
        request.getRequestDispatcher("/recipe_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
