package com.github.liliangshan.prometheus.metric;

import com.github.liliangshan.metric.api.MtGauge;
import com.github.liliangshan.prometheus.PrometheusObject;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.function.ToDoubleFunction;

/**
 * PrometheusGauge .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public class PrometheusGauge extends AbstractPrometheusMetric implements MtGauge {

    private static final Logger logger = LoggerFactory.getLogger(PrometheusGauge.class);

    private final Gauge gauge;
    private final Callable<Double> callable;
    private final ToDoubleFunction<Callable<Double>> function = callable -> {
        if (callable == null) {
            return 0L;
        }
        try {
            return callable.call();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0L;
        }
    };

    public PrometheusGauge(CollectorRegistry registry, PrometheusObject prometheusObject) {
        super(registry, prometheusObject);
        this.gauge = Gauge.build()
                .name(prometheusObject.getName())
                .namespace(prometheusObject.getNamespace())
                .help(prometheusObject.getHelp())
                .subsystem(prometheusObject.getSubsystem())
                .unit(prometheusObject.getUnit())
                .labelNames(this.getLabelNames())
                .register(registry);
        this.callable = prometheusObject.getCallable();
    }

    @Override
    public Callable<Double> getCallable() {
        return this::updateAndGetValue;
    }

    @Override
    public double updateAndGetValue() {
        gauge.labels(this.getLabelValues()).set(function.applyAsDouble(callable));
        return gauge.labels(this.getLabelValues()).get();
    }

    @Override
    public void remove(String... labelValues) {
        gauge.remove(labelValues);
    }

    @Override
    public void clear() {
        gauge.clear();
    }

}
