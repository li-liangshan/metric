package com.saicmotor.micrometer.binder;

import com.saicmotor.metric.api.MtCounter;
import com.saicmotor.micrometer.AbstractInstrumentBinder;
import com.saicmotor.micrometer.Instrument;
import com.saicmotor.micrometer.metric.InstrumentCounter;

/**
 * IncrementBinder .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public class CounterBinder extends AbstractInstrumentBinder<MtCounter> {

    @Override
    protected MtCounter registerInternal(Instrument instrument) {
        return new InstrumentCounter(instrument, meterRegistry);
    }

    @Override
    protected void checkInstrument(Instrument instrument) {

    }

}
