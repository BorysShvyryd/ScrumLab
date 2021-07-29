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

@WebServlet("/app/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginedAdmin = AdminDao.getLoginedAdmin(request.getSession());
        request.setAttribute("loginedAdmin", loginedAdmin);
        request.setAttribute("numberRecipe", RecipeDao.getNumberRecipeByAdmin(loginedAdmin.getId()));
        request.setAttribute("numberPlan", PlanDao.getNumberPlanByAdmin(loginedAdmin.getId()));

        //#5 PlanDao - metoda pobierająca ostatnio dodany plan
//        String[][] planOrd = {{"poniedziałek","Śniadanie","Przepis 2","Opis przepisu 2"},
//                {"poniedziałek","Kolacja","Przepis 1","Opis przepisu 1"},
//                {"wtorek", "Śniadanie", "Przepis 3", "Opis przepisu 3"},
//                {"wtorek", "Kolacja", "Przepis 1", "Opis przepisu 1"}};

//        Plan plan = new Plan(1,"Name", "description", "new Date()", 1);
//        request.setAttribute("lastPlan", plan);
//        request.setAttribute("descriptionPlan", plan.getAdminId());

        //request.setAttribute("lastPlan", PlanDao.LastPlan(loginedAdmin.getId()));

        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
