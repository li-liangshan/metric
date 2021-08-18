package com.github.liliangshan.micrometer.binder;

import com.github.liliangshan.metric.api.MtGauge;
import com.github.liliangshan.micrometer.Instrument;
import com.github.liliangshan.micrometer.metric.InstrumentGauge;
import com.github.liliangshan.micrometer.AbstractInstrumentBinder;
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
