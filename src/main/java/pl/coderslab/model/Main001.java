package pl.coderslab.model;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/main001")
public class Main001 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DayNameDao dayNameDao = new DayNameDao();
        System.out.println(dayNameDao.findAll());
        response.getWriter().append(dayNameDao.findAll().toString());

        response.getWriter().append("\n");
        PlanDao planDao = new PlanDao();
        System.out.println(planDao.findAll());
        response.getWriter().append(planDao.findAll().toString());
        response.getWriter().append("\n");
        try {
            System.out.println(planDao.lastPlan(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
