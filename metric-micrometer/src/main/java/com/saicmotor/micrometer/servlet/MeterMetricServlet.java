package com.saicmotor.micrometer.servlet;

import com.saicmotor.metric.servlet.AbstractMetricServlet;
import com.saicmotor.micrometer.client.MeterMetricClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MeterMetricServlet .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class MeterMetricServlet extends AbstractMetricServlet {

    public MeterMetricServlet(MeterMetricClient client) {
        super(client);
    }

    @Override
    protected String getMetricPatchSpecPrefix() {
        return "";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
