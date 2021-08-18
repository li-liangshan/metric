package com.github.liliangshan.micrometer.binder;

import com.github.liliangshan.metric.api.MtTimer;
import com.github.liliangshan.micrometer.Instrument;
import com.github.liliangshan.micrometer.metric.InstrumentTimer;
import com.github.liliangshan.micrometer.AbstractInstrumentBinder;

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
