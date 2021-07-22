package pl.coderslab.model;

import pl.coderslab.dao.DayNameDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/main001")
public class Main001 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DayNameDao dayNameDao = new DayNameDao();
        System.out.println(dayNameDao.findAll());
        response.getWriter().append(dayNameDao.findAll().toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
