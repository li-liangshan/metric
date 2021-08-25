package com.github.liliangshan.samples.prometheus;

import com.github.liliangshan.metric.service.Service;
import com.github.liliangshan.samples.prometheus.service.CounterService;
import com.github.liliangshan.samples.prometheus.service.GaugeService;
import com.github.liliangshan.samples.prometheus.servlet.PingServlet;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.github.liliangshan.metric.api.MtCounter;
import com.github.liliangshan.metric.api.MtGauge;
import com.github.liliangshan.prometheus.client.PrometheusMetricClient;
import com.github.liliangshan.prometheus.servlet.PrometheusMetricServlet;
import com.github.liliangshan.web.HttpServer;
import io.prometheus.client.CollectorRegistry;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Random;

/**
 * PrometheusApplication .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class PrometheusApplication {

    public static void main(String[] args) {

        PrometheusMetricClient client = new PrometheusMetricClient(CollectorRegistry.defaultRegistry);
        PrometheusMetricServlet prometheusMetricServlet = new PrometheusMetricServlet(client);

        // add service
        Service counterService = new CounterService();
        prometheusMetricServlet.addService(counterService);
        Service gaugeService = new GaugeService();
        prometheusMetricServlet.addService(gaugeService);

        // start server
        HttpServer server = new HttpServer(3201, prometheusMetricServlet);
        server.addServlet(new PingServlet(client));
        server.run();
    }

}
