package com.saicmotor.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HomeServlet .
 *
 * @author liliangshan
 * @date 2021/8/14
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().print("<html>\n"
                + "<head><title>Metric Web</title></head>\n"
                + "<body>\n"
                + "<h1>Metric Web</h1>\n"
                + "<p><a href=\"/metrics\">Metrics</a></p>\n"
                + "</body>\n"
                + "</html>");
    }
}
