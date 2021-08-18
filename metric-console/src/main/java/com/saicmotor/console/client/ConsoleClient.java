package com.saicmotor.console.client;

import com.saicmotor.console.metric.ConsoleCounter;
import com.saicmotor.console.metric.ConsoleGauge;
import com.saicmotor.console.metric.ConsoleTimer;
import com.saicmotor.console.registry.ConsoleRegistry;
import com.saicmotor.metric.api.MtCounter;
import com.saicmotor.metric.api.MtGauge;
import com.saicmotor.metric.api.MtTimer;
import com.saicmotor.metric.client.AbstractClient;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * ConsoleClient .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public class ConsoleClient extends AbstractClient {

    private final long period;
    private final long timerLimit;
    private final ConsoleRegistry registry;

    public ConsoleClient(ConsoleRegistry registry, long period, long timerLimit) {
        this.registry = registry;
        this.period = period;
        this.timerLimit = timerLimit;
    }

    public ConsoleRegistry getRegistry() {
        return registry;
    }

    @Override
    protected MtCounter getCounter(String name, String description, Map<String, String> tags) {
        ConsoleCounter counter = new ConsoleCounter(name, description, tags);
        return counter.register(registry);
    }

    @Override
    protected MtGauge getGauge(String name, String description, Map<String, String> tags, Callable<Double> callable) {
        ConsoleGauge gauge = new ConsoleGauge(period, name, description, tags, callable);
        return gauge.register(registry);
    }

    @Override
    protected MtTimer getTimer(String name, String description, Map<String, String> tags) {
        ConsoleTimer timer = new ConsoleTimer(timerLimit, name, description, tags);
        return timer.register(registry);
    }
}
