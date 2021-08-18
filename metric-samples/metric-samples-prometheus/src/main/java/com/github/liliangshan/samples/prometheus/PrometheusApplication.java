package com.github.liliangshan.samples.prometheus;

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
        final Random random = new Random();
        PrometheusMetricClient client = new PrometheusMetricClient(CollectorRegistry.defaultRegistry);
        MtCounter counter = client.counter("request_name", "this is request name",
                Maps.asMap(Sets.newHashSet("tag1", "tag2"), new Function<String, String>() {
                    @Override
                    public @Nullable String apply(@Nullable String s) {
                        return s + random.nextInt();
                    }
                }));
        counter.increment();

        MtGauge gauge = client.gauge("request_mem", "this is request mem", Maps.asMap(Sets.newHashSet("tag1", "tag2"), new Function<String, String>() {
            @Override
            public @Nullable String apply(@Nullable String s) {
                return s + "_value";
            }
        }), random::nextDouble);

        gauge.value();

        HttpServer server = new HttpServer(3201, new PrometheusMetricServlet(client));
        server.run();
    }

}
