package com.saicmotor.micrometer;

import com.saicmotor.metric.api.Metric;
import io.micrometer.core.instrument.binder.MeterBinder;

/**
 * InstrumentBinder .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public interface InstrumentBinder<T extends Metric> extends MeterBinder {

    void clear();

    void destroy();

    T register(Instrument instrument);

}
