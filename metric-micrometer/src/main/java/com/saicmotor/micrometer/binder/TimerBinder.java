package com.saicmotor.micrometer.binder;

import com.saicmotor.metric.api.MtTimer;
import com.saicmotor.metric.micrometer.AbstractInstrumentBinder;
import com.saicmotor.metric.micrometer.Instrument;
import com.saicmotor.micrometer.metric.InstrumentTimer;

/**
 * TimerBinder .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public class TimerBinder extends AbstractInstrumentBinder<MtTimer> {

    @Override
    protected MtTimer registerInternal(Instrument instrument) {
        return new InstrumentTimer(instrument, meterRegistry);

    }

    @Override
    protected void checkInstrument(Instrument instrument) {

    }

}
