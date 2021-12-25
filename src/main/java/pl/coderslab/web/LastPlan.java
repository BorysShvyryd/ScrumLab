//package pl.coderslab.web;
//
////import pl.coderslab.dao.PlanDao;
////import pl.coderslab.model.PlanList;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//import static pl.coderslab.dao.PlanDao.lastPlan;
//
//@WebServlet("/lastPlan")
//public class LastPlan extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        try {
////            List<PlanList> theLast = lastPlan(1);
////            System.out.println(theLast);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//
//        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
