package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.utils.DbUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/app/plan/details")
public class PlanDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<List<String>> listDetailsPlan = PlanDao.getDetailsFromPlan(Integer.parseInt(request.getParameter("plan_id")));
        request.setAttribute("listDetailsPlan", listDetailsPlan);
        request.setAttribute("plan", PlanDao.read(Integer.parseInt(request.getParameter("plan_id"))));
        request.getRequestDispatcher("/plan_details_schegules.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
