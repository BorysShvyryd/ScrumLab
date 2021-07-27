package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/planList")
public class PlanListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Plan> plans = PlanDao.findAll();
        plans.sort(Comparator.comparing(Plan::getCreated).reversed());
        request.setAttribute("plan", plans);
        getServletContext().getRequestDispatcher("/planlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
