package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/app/plan/delete")
public class PlanDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String someMessage = "Czy na pewno chcesz usunąć przepis z planu?";

        response.setContentType("text/html");
        response.getWriter().print(
                "<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
//        response.getWriter().print(
//                "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\"%>\n");
        response.getWriter().print(
                "<%@include file=\"headerLogined.jsp\"%>");
        response.getWriter().print(
                "<script type=\"text/javascript\">confirm(" + someMessage + ");</script>");
        response.getWriter().print(
                "<%@include file=\"footerLogined.jsp\"%>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
