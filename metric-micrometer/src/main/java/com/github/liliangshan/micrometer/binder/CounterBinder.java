package com.github.liliangshan.micrometer.binder;

import com.github.liliangshan.metric.api.MtCounter;
import com.github.liliangshan.micrometer.metric.InstrumentCounter;
import com.github.liliangshan.micrometer.AbstractInstrumentBinder;
import com.github.liliangshan.micrometer.Instrument;

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
