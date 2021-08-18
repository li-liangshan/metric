package com.github.liliangshan.micrometer.client;

import com.github.liliangshan.micrometer.binder.CounterBinder;
import com.google.common.base.Preconditions;
import com.github.liliangshan.metric.api.MtCounter;
import com.github.liliangshan.metric.api.MtGauge;
import com.github.liliangshan.metric.api.MtTimer;
import com.github.liliangshan.metric.client.AbstractClient;
import com.github.liliangshan.micrometer.Instrument;
import com.github.liliangshan.micrometer.binder.GaugeBinder;
import com.github.liliangshan.micrometer.binder.TimerBinder;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * MicroMeterClient .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public class MeterMetricClient extends AbstractClient {

    private final CounterBinder counterBinder;
    private final GaugeBinder gaugeBinder;
    private final TimerBinder timerBinder;

    private final MeterRegistry meterRegistry;

    public MeterMetricClient(MeterRegistry meterRegistry) {
        this(meterRegistry, new CounterBinder(), new GaugeBinder(), new TimerBinder());
    }

    public MeterMetricClient(MeterRegistry meterRegistry, CounterBinder counterBinder, GaugeBinder gaugeBinder, TimerBinder timerBinder) {
        this.meterRegistry = meterRegistry;
        this.counterBinder = counterBinder;
        this.gaugeBinder = gaugeBinder;
        this.timerBinder = timerBinder;
        this.initialized();
    }

    @Override
    protected void initialized() {
        if (counterBinder != null) {
            this.counterBinder.bindTo(meterRegistry);
        }
        if (gaugeBinder != null) {
            this.gaugeBinder.bindTo(meterRegistry);
        }
        if (timerBinder != null) {
            this.timerBinder.bindTo(meterRegistry);
        }
    }

    @Override
    protected MtCounter getCounter(String name, String description, Map<String, String> tags) {
        Preconditions.checkArgument(counterBinder != null, "counterBinder is null");
        return counterBinder.register(new Instrument(name, description, tags));
    }

    @Override
    protected MtGauge getGauge(String name, String description, Map<String, String> tags, Callable<Double> callable) {
        Preconditions.checkArgument(gaugeBinder != null, "gaugeBinder is null");
        return gaugeBinder.register(new Instrument(name, description, tags, callable));
    }

    @Override
    protected MtTimer getTimer(String name, String description, Map<String, String> tags) {
        Preconditions.checkArgument(timerBinder != null, "timerBinder is null");
        return timerBinder.register(new Instrument(name, description, tags));
    }

}
