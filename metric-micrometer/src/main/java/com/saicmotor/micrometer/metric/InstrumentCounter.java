package com.saicmotor.micrometer.metric;

import com.saicmotor.metric.api.MtCounter;
import com.saicmotor.metric.micrometer.Instrument;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * InstrumentCounter .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public class InstrumentCounter extends AbstractInstrumentMetric implements MtCounter {

    private final Counter counter;

    public InstrumentCounter(Instrument instrument, MeterRegistry registry) {
        super(instrument);
        this.counter = Counter.builder(this.getName())
                .tags(this.getTags(tags))
                .description(this.getDescription())
                .register(registry);
    }

    @Override
    public Meter.Id getMeterId() {
        return counter.getId();
    }

    @Override
    public Iterable<Measurement> measure() {
        return counter.measure();
    }

    @Override
    protected void closeInternal() {
        counter.close();
    }

    @Override
    public void increment() {
        this.counter.increment();
    }

    @Override
    public void increment(long delta) {
        this.counter.increment(delta);
    }

    @Override
    public long count() {
        return (long) counter.count();
    }

}
