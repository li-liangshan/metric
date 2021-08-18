package com.github.liliangshan.micrometer.client;

import com.github.liliangshan.micrometer.binder.CounterBinder;
import com.github.liliangshan.micrometer.binder.GaugeBinder;
import com.github.liliangshan.micrometer.binder.TimerBinder;
import io.micrometer.prometheus.PrometheusMeterRegistry;

/**
 * PrometheusMeterClient .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class PrometheusMeterClient extends MeterMetricClient {

    private final PrometheusMeterRegistry meterRegistry;

    public PrometheusMeterClient(PrometheusMeterRegistry meterRegistry) {
        super(meterRegistry);
        this.meterRegistry = meterRegistry;
    }

    public PrometheusMeterClient(PrometheusMeterRegistry meterRegistry, CounterBinder counterBinder, GaugeBinder gaugeBinder, TimerBinder timerBinder) {
        super(meterRegistry, counterBinder, gaugeBinder, timerBinder);
        this.meterRegistry = meterRegistry;
    }

    public PrometheusMeterRegistry getMeterRegistry() {
        return meterRegistry;
    }

}
