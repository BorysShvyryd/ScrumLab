package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/plan/edit")
public class EditPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        request.setAttribute("loginedAdmin", loginedAdmin);
        request.setAttribute("adminPlan", PlanDao.read(Integer.parseInt(request.getParameter("plan_id"))));

        request.getRequestDispatcher("/edit_plan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPlan = request.getParameter("id");
        Plan plan = PlanDao.read(Integer.parseInt(idPlan));
        plan.setName(request.getParameter("name"));
        plan.setDescription(request.getParameter("description"));
        PlanDao.update(plan);
        response.sendRedirect("/app/plan/list");
    }
}
