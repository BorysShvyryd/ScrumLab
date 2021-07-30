package pl.coderslab.web;

import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/recipe/plan/delete")
public class DeleteRecipPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipePlanDao.deleteRecipePlanInDB(Integer.parseInt(request.getParameter("id")));
//      Question -> OK,Cancel  request.getRequestDispatcher("#").forward(request, response);
        response.sendRedirect("/app/plan/details?plan_id=" + request.getParameter("plan_id"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
