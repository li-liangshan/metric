package com.github.liliangshan.prometheus.servlet;

import com.github.liliangshan.metric.service.Service;
import com.github.liliangshan.metric.servlet.AbstractMetricServlet;
import com.github.liliangshan.prometheus.client.PrometheusMetricClient;
import com.google.common.collect.Lists;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

/**
 * PrometheusMetricServlet .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class PrometheusMetricServlet extends AbstractMetricServlet {

    private final CollectorRegistry registry;
    private final List<Service> services = Lists.newLinkedList();

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
        this.updateMetrics();

        try (BufferedWriter writer = new BufferedWriter(resp.getWriter())) {
            Set<String> names = this.parse(req);
            TextFormat.writeFormat(contentType, writer, registry.filteredMetricFamilySamples(names));
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

    public void addService(Service service) {
        if (service != null) {
            services.add(service);
        }
    }

    @Override
    protected void updateMetricsBefore() {
        services.forEach(it -> {
            try {
                it.execute(client);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    @Override
    protected void updateMetricsAfter() {

    }

}
