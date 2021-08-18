package com.github.liliangshan.prometheus.metric;

import com.github.liliangshan.metric.AbstractMetric;
import com.github.liliangshan.prometheus.PrometheusObject;
import io.prometheus.client.CollectorRegistry;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AbstractPrometheusMetric .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public abstract class AbstractPrometheusMetric extends AbstractMetric {

    private final AtomicBoolean closed = new AtomicBoolean(false);

    protected CollectorRegistry registry;

    protected AbstractPrometheusMetric(CollectorRegistry registry, PrometheusObject prometheusObject) {
        super(prometheusObject.getName(), prometheusObject.getHelp(), prometheusObject.getTags());
        this.registry = registry;
    }

    protected String[] getLabelNames() {
        List<String> labelNames = this.getTagNames();
        return labelNames.toArray(new String[0]);
    }

    protected String[] getLabelValues() {
        List<String> labelValues = this.getTagValues();
        return labelValues.toArray(new String[0]);
    }

    public abstract void remove(String... labelValues);

    public abstract void clear();

}
