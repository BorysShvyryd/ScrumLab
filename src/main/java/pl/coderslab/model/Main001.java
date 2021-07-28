package pl.coderslab.model;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/main001")
public class Main001 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*        DayNameDao dayNameDao = new DayNameDao();*/
/*        System.out.println(dayNameDao.findAll());
        response.getWriter().append(dayNameDao.findAll().toString());

        response.getWriter().append("\n");
        PlanDao planDao = new PlanDao();
        System.out.println(planDao.findAll());
        response.getWriter().append(planDao.findAll().toString());*/

        Admin admin = new Admin();
        admin.setFirstName("Jan");
        admin.setLastName("Kowalski");
        admin.setEmail("probny@mail.pl");
        admin.setPassword("trololo");
        admin.setId(3);
        admin.setEnable(3);
        AdminDao adminDao = new AdminDao();
        adminDao.create(admin);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
