package com.saicmotor.micrometer.binder;

import com.saicmotor.metric.api.MtGauge;
import com.saicmotor.metric.micrometer.AbstractInstrumentBinder;
import com.saicmotor.metric.micrometer.Instrument;
import com.saicmotor.micrometer.metric.InstrumentGauge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * GaugeBinder .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public class GaugeBinder extends AbstractInstrumentBinder<MtGauge> {

    private static final Logger logger = LoggerFactory.getLogger(GaugeBinder.class);

    @Override
    protected MtGauge registerInternal(Instrument instrument) {
        return new InstrumentGauge(instrument, this.meterRegistry);
    }

    @Override
    protected void checkInstrument(Instrument instrument) {

    }

}
