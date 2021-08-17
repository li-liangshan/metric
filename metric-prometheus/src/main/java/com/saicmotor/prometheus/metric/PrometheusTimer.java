package com.saicmotor.prometheus.metric;

import com.saicmotor.metric.api.MtTimer;
import com.saicmotor.metric.prometheus.PrometheusObject;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Summary;

import java.util.concurrent.TimeUnit;

/**
 * PrometheusTimer .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public class PrometheusTimer extends AbstractPrometheusMetric implements MtTimer {

    private final Summary summary;

    public PrometheusTimer(CollectorRegistry registry, PrometheusObject prometheusObject) {
        super(registry, prometheusObject);
        this.summary = Summary.build()
                .name(prometheusObject.getName())
                .namespace(prometheusObject.getNamespace())
                .help(prometheusObject.getHelp())
                .subsystem(prometheusObject.getSubsystem())
                .labelNames(this.getLabelNames())
                .register(registry);
    }

    @Override
    public void record(long timeMillis) {
        summary.labels(this.getLabelValues()).observe(timeMillis);
    }

    @Override
    public void record(long time, TimeUnit unit) {
        long timeMills = TimeUnit.MILLISECONDS.convert(time, unit);
        summary.labels(this.getLabelValues()).observe(timeMills);
    }

    @Override
    public void remove(String... labelValues) {
        summary.remove(labelValues);
    }

    @Override
    public void clear() {
        summary.clear();
    }

}
