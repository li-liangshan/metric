package com.saicmotor.console;

import com.saicmotor.console.metric.ConsoleCounter;
import com.saicmotor.console.metric.ConsoleGauge;
import com.saicmotor.console.metric.ConsoleTimer;
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

    public ConsoleClient(long period, long timerLimit) {
        this.period = period;
        this.timerLimit = timerLimit;
    }

    @Override
    protected MtCounter getCounter(String name, String description, Map<String, String> tags) {
        return new ConsoleCounter(name, description, tags);
    }

    @Override
    protected MtGauge getGauge(String name, String description, Map<String, String> tags, Callable<Double> callable) {
        return new ConsoleGauge(period, name, description, tags, callable);
    }

    @Override
    protected MtTimer getTimer(String name, String description, Map<String, String> tags) {
        return new ConsoleTimer(timerLimit, name, description, tags);
    }
}
