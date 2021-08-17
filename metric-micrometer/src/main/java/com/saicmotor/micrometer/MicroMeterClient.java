package com.saicmotor.micrometer;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.saicmotor.metric.api.MtCounter;
import com.saicmotor.metric.api.MtGauge;
import com.saicmotor.metric.api.MtTimer;
import com.saicmotor.metric.client.AbstractClient;
import com.saicmotor.metric.micrometer.Instrument;
import com.saicmotor.micrometer.binder.CounterBinder;
import com.saicmotor.micrometer.binder.GaugeBinder;
import com.saicmotor.micrometer.binder.TimerBinder;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * MicroMeterClient .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public class MicroMeterClient extends AbstractClient {

    private final CounterBinder counterBinder;
    private final GaugeBinder gaugeBinder;
    private final TimerBinder timerBinder;

    private final MeterRegistry meterRegistry;

    public MicroMeterClient(MeterRegistry meterRegistry) {
        this(meterRegistry, new CounterBinder(), new GaugeBinder(), new TimerBinder());
    }

    public MicroMeterClient(MeterRegistry meterRegistry, CounterBinder counterBinder, GaugeBinder gaugeBinder, TimerBinder timerBinder) {
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
        Preconditions.checkArgument(counterBinder == null, "counterBinder is null");
        return counterBinder.register(new Instrument(name, description, tags));
    }

    @Override
    protected MtGauge getGauge(String name, String description, Map<String, String> tags, Callable<Double> callable) {
        Preconditions.checkArgument(gaugeBinder == null, "gaugeBinder is null");
        return gaugeBinder.register(new Instrument(name, description, tags, callable));
    }

    @Override
    protected MtTimer getTimer(String name, String description, Map<String, String> tags) {
        Preconditions.checkArgument(timerBinder == null, "timerBinder is null");
        return timerBinder.register(new Instrument(name, description, tags));
    }

}
