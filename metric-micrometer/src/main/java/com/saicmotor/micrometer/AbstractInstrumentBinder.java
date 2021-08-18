package com.saicmotor.micrometer;

import com.saicmotor.metric.api.Metric;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * AbstractInstrumentBinder .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public abstract class AbstractInstrumentBinder<T extends Metric> implements InstrumentBinder<T> {

    protected MeterRegistry meterRegistry;

    @Override
    public void clear() {
        if (meterRegistry != null && !meterRegistry.isClosed()) {
            meterRegistry.clear();
        }
    }

    @Override
    public void destroy() {
        if (meterRegistry != null && !meterRegistry.isClosed()) {
            meterRegistry.close();
        }
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public T  register(Instrument instrument) {
        if (meterRegistry == null || meterRegistry.isClosed()) {
            throw new IllegalStateException("Metrics registry is not initialized yet!");
        }
        return this.registerInternal(instrument);
    }

    protected abstract T registerInternal(Instrument instrument);

    protected abstract void checkInstrument(Instrument instrument);

}
