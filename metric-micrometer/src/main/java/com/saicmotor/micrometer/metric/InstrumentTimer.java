package com.saicmotor.micrometer.metric;

import com.saicmotor.metric.api.MtTimer;
import com.saicmotor.metric.micrometer.Instrument;
import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.util.concurrent.TimeUnit;

/**
 * InstrumentTimer .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public class InstrumentTimer extends AbstractInstrumentMetric implements MtTimer {

    private final Timer timer;

    public InstrumentTimer(Instrument instrument, MeterRegistry registry) {
        super(instrument);
        timer = Timer.builder(instrument.getName())
                .tags(this.getTags(instrument.getTags()))
                .description(instrument.getDescription())
                .register(registry);
    }

    @Override
    public void record(long timeMillis) {
        timer.record(timeMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public void record(long time, TimeUnit unit) {
        timer.record(time, unit);
    }

    @Override
    public Meter.Id getMeterId() {
        return timer.getId();
    }

    @Override
    public Iterable<Measurement> measure() {
        return timer.measure();
    }

    @Override
    protected void closeInternal() {
        timer.close();
    }

}
