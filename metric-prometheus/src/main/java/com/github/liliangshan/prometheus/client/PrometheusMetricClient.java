package com.github.liliangshan.prometheus.client;

import com.github.liliangshan.metric.api.MtCollector;
import com.github.liliangshan.metric.api.MtCounter;
import com.github.liliangshan.metric.api.MtGauge;
import com.github.liliangshan.metric.api.MtTimer;
import com.github.liliangshan.metric.client.AbstractClient;
import com.github.liliangshan.prometheus.PromContainer;
import com.github.liliangshan.prometheus.PromInstrument;
import com.github.liliangshan.prometheus.PrometheusObject;
import com.github.liliangshan.prometheus.collector.PromCollector;
import com.github.liliangshan.prometheus.collector.PromInstrumentCollector;
import com.github.liliangshan.prometheus.metric.PrometheusCounter;
import com.github.liliangshan.prometheus.metric.PrometheusGauge;
import com.github.liliangshan.prometheus.metric.PrometheusTimer;
import io.prometheus.client.CollectorRegistry;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * PrometheusMetricClient .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public class PrometheusMetricClient extends AbstractClient implements PrometheusInstrumentClient {

    private final CollectorRegistry registry;

    public PrometheusMetricClient(CollectorRegistry registry) {
        this.registry = registry;
    }

    public CollectorRegistry getRegistry() {
        return registry;
    }

    @Override
    protected MtCounter getCounter(String name, String description, Map<String, String> tags) {
        return new PrometheusCounter(registry, new PrometheusObject(name, description, tags));
    }

    @Override
    protected MtGauge getGauge(String name, String description, Map<String, String> tags,
                               Callable<Double> callable) {
        return new PrometheusGauge(registry, new PrometheusObject(name, description, tags, callable));
    }

    @Override
    protected MtTimer getTimer(String name, String description, Map<String, String> tags) {
        return new PrometheusTimer(registry, new PrometheusObject(name, description, tags));
    }

    @Override
    protected MtCollector getMtCollector(String name, String description, Map<String, String> tags, Callable<?> callable) {
        return new PromInstrumentCollector(registry, new PromInstrument(name, description, tags, (Callable<List<PromContainer>>) callable));
    }

    @Override
    public PromCollector promCollector(String name, String description, Map<String, String> tags,
                                       Callable<List<PromContainer>> callable) {
        return (PromCollector) this.collector(name, description, tags, callable);
    }

}
