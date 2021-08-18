package com.github.liliangshan.metric.client;

import com.github.liliangshan.metric.MetricId;
import com.github.liliangshan.metric.api.MtCounter;
import com.github.liliangshan.metric.api.MtGauge;
import com.github.liliangshan.metric.api.MtTimer;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AbstractClient .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public abstract class AbstractClient implements Client {

    private final Map<MetricId, MtGauge> gauges = new ConcurrentHashMap<>();
    private final Map<MetricId, MtCounter> counters = new ConcurrentHashMap<>();
    private final Map<MetricId, MtTimer> timers = new ConcurrentHashMap<>();

    protected AbstractClient() {
        this.initialized();
    }

    protected void initialized() {

    }

    @Override
    public MtCounter counter(String name, String description, Map<String, String> tags) {
        MetricId key = this.getKey(name, tags);
        MtCounter counter = counters.get(key);
        if (counter == null) {
            counter = counters.computeIfAbsent(key, k -> getCounter(name, description, tags));
        }
        return counter;
    }

    protected abstract MtCounter getCounter(String name, String description, Map<String, String> tags);

    @Override
    public MtGauge gauge(String name, String description, Map<String, String> tags, Callable<Double> callable) {
        MetricId key = this.getKey(name, tags);
        MtGauge gauge = gauges.get(key);
        if (gauge == null) {
            gauge = gauges.computeIfAbsent(key, k -> getGauge(name, description, tags, callable));
        }
        return gauge;
    }

    protected abstract MtGauge getGauge(String name, String description, Map<String, String> tags, Callable<Double> callable);

    @Override
    public MtTimer timer(String name, String description, Map<String, String> tags) {
        MetricId key = this.getKey(name, tags);
        MtTimer timer = timers.get(key);
        if (timer == null) {
            timer = timers.computeIfAbsent(key, k -> getTimer(name, description, tags));
        }
        return timer;
    }

    protected abstract MtTimer getTimer(String name, String description, Map<String, String> tags);

    protected MetricId getKey(String name, Map<String, String> map) {
        return new MetricId(name, map);
    }

    @Override
    public void destroy() {
        gauges.clear();
        counters.clear();
        timers.clear();
    }



}
