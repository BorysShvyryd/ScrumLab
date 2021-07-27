package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.PlanList;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/lastPlan")
public class LastPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanList planList = new PlanList();
        PlanDao planDao = new PlanDao();


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
