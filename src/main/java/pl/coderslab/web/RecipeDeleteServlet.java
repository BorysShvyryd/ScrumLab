package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/recipe/delete")
public class RecipeDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id", request.getParameter("id"));
//        request.setAttribute("hrefServlet", request.getParameter("/app/recipe/delete"));
//        request.setAttribute("h3_text", request.getParameter("Czy na pewno chcesz usunąć przepis?"));
//        request.setAttribute("buttom_name", request.getParameter("id"));
        request.setAttribute("recipe_name", RecipeDao.read(Integer.parseInt(request.getParameter("id"))).getName());

        request.getRequestDispatcher("/recipe-delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/app/recipe/list");
    }
}
