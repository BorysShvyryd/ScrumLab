package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/app/plan/delete")
public class PlanDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id", request.getParameter("plan_id"));
        request.setAttribute("plan_name", PlanDao.read(Integer.parseInt(request.getParameter("plan_id"))).getName());

        request.getRequestDispatcher("/plan-delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao.delete(Integer.parseInt(request.getParameter("plan_id")));
        response.sendRedirect("/app/plan/list");
    }
}
