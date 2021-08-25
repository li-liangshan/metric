package com.github.liliangshan.micrometer.metric;

import com.github.liliangshan.metric.api.MtGauge;
import com.github.liliangshan.micrometer.Instrument;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.function.ToDoubleFunction;

/**
 * InstrumentGauge .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public class InstrumentGauge extends AbstractInstrumentMetric implements MtGauge {

    private static final Logger logger = LoggerFactory.getLogger(InstrumentGauge.class);

    private final Gauge gauge;

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

    public InstrumentGauge(Instrument instrument, MeterRegistry registry) {
        super(instrument);
        gauge = Gauge.builder(instrument.getName(), instrument.getCallable(), function)
                .tags(this.getTags(instrument.getTags()))
                .description(instrument.getDescription())
                .register(registry);
    }

    @Override
    public Callable<Double> getCallable() {
        return gauge::value;
    }

    @Override
    public double updateAndGetValue() {
        return gauge.value();
    }

    @Override
    public Meter.Id getMeterId() {
        return gauge.getId();
    }

    @Override
    public Iterable<Measurement> measure() {
        return gauge.measure();
    }

    @Override
    protected void closeInternal() {
        gauge.close();
    }

}
