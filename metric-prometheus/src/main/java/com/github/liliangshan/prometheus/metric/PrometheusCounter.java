package com.github.liliangshan.prometheus.metric;

import com.github.liliangshan.metric.api.MtCounter;
import com.github.liliangshan.prometheus.PrometheusObject;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;


/**
 * PrometheusCounter .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public class PrometheusCounter extends AbstractPrometheusMetric implements MtCounter {

    private final Counter counter;

    public PrometheusCounter(CollectorRegistry registry, PrometheusObject prometheusObject) {
        super(registry, prometheusObject);
        this.counter = Counter.build()
                .name(prometheusObject.getName())
                .help(prometheusObject.getHelp())
                .namespace(prometheusObject.getNamespace())
                .subsystem(prometheusObject.getSubsystem())
                .unit(prometheusObject.getUnit())
                .labelNames(this.getLabelNames())
                .register(registry);
    }

    @Override
    public void increment() {
        counter.labels(this.getLabelValues()).inc();
    }

    @Override
    public void increment(long delta) {
        counter.labels(this.getLabelValues()).inc(delta);
    }

    @Override
    public long count() {
        return (long) counter.labels(this.getLabelValues()).get();
    }

    @Override
    public void remove(String... labelValues) {
        counter.remove(labelValues);
    }

    @Override
    public void clear() {
        counter.clear();
    }

}
