package com.github.liliangshan.micrometer.servlet;

import com.github.liliangshan.micrometer.client.PrometheusMeterClient;
import io.micrometer.prometheus.PrometheusMeterRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * PrometheusMeterMetricServlet .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class PrometheusMeterMetricServlet extends MeterMetricServlet {

    private final PrometheusMeterRegistry meterRegistry;

    public PrometheusMeterMetricServlet(PrometheusMeterClient client) {
        super(client);
        this.meterRegistry = client.getMeterRegistry();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        BufferedWriter writer = new BufferedWriter(resp.getWriter());
        try {
            meterRegistry.scrape(writer);
        } catch (IOException e) {
            logger.error("metrics error", e);
            resp.sendError(500, e.getMessage());
        } finally {
            writer.flush();
        }
    }

}
