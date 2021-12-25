package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/plan/add")
public class AddPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        request.setAttribute("loginedAdmin", loginedAdmin);

        request.getRequestDispatcher("/add_plan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");
        Plan plan = new Plan(0,
                planName,
                planDescription,
                "",
                AdminDao.getLoginedAdmin(request.getSession()).getId());
        if ((!"".equals(planName)) && (!"".equals(planDescription))) {
            PlanDao.create(plan);
            response.sendRedirect("/app/plan/list");
        } else {
            request.setAttribute("planName",planName);
            request.setAttribute("planDescription",planDescription);
            request.getRequestDispatcher("/add_plan.jsp").forward(request, response);
        }
    }
}
