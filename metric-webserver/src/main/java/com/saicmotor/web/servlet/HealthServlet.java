package com.saicmotor.web.servlet;

import com.saicmotor.metric.servlet.AbstractHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HealthServlet .
 *
 * @author liliangshan
 * @date 2021/8/14
 */
public class HealthServlet extends AbstractHttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().print("ok");
    }

    @Override
    public String getPathSpec() {
        return "/health";
    }

}
