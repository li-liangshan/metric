package com.saicmotor.prometheus.servlet;

import com.saicmotor.metric.servlet.AbstractMetricServlet;
import com.saicmotor.prometheus.client.PrometheusMetricClient;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * PrometheusMetricServlet .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class PrometheusMetricServlet extends AbstractMetricServlet {

    private final CollectorRegistry registry;

    public PrometheusMetricServlet(PrometheusMetricClient client) {
        super(client);
        this.registry = client.getRegistry();
    }

    @Override
    protected String getMetricPatchSpecPrefix() {
        return "";
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        String contentType = TextFormat.chooseContentType(req.getHeader("Accept"));
        resp.setContentType(contentType);

        try (BufferedWriter writer = new BufferedWriter(resp.getWriter())) {
            TextFormat.writeFormat(contentType, writer, registry.filteredMetricFamilySamples(this.parse(req)));
            writer.flush();
        }
    }

    private Set<String> parse(HttpServletRequest req) {
        String[] includedParam = req.getParameterValues("name[]");
        if (includedParam == null) {
            return Collections.emptySet();
        }
        return new HashSet<>(Arrays.asList(includedParam));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doGet(req, resp);
    }

}
