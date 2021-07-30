package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/app/*")


public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpSession session = request1.getSession();
        HttpServletResponse response1 = (HttpServletResponse) response;
        if (session.getAttribute("loginedAdmin") == null) {
            response1.sendRedirect("/login");
        } else {
            chain.doFilter(request, response);
        }
    }
}
