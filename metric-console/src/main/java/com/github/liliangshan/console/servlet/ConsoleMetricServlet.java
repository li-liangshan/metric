package com.github.liliangshan.console.servlet;

import com.github.liliangshan.console.client.ConsoleClient;
import com.github.liliangshan.console.registry.ConsoleRegistry;
import com.github.liliangshan.metric.servlet.AbstractMetricServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * ConsoleMetricServlet .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class ConsoleMetricServlet extends AbstractMetricServlet {

    private final ConsoleRegistry registry;

    public ConsoleMetricServlet(ConsoleClient client) {
        super(client);
        registry = client.getRegistry();
    }

    @Override
    protected String getMetricPatchSpecPrefix() {
        return "";
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        BufferedWriter writer = new BufferedWriter(resp.getWriter());
        try {
            registry.scrape(writer);
        } catch (IOException e) {
            logger.error("console metrics error", e);
            resp.sendError(500, e.getMessage());
        } finally {
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doGet(req, resp);
    }

}
