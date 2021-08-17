package com.saicmotor.prometheus.collector;

import com.google.common.collect.Lists;
import com.saicmotor.metric.api.Metric;
import com.saicmotor.prometheus.PromContainer;
import com.saicmotor.prometheus.PromInstrument;
import io.prometheus.client.CollectorRegistry;

import java.util.List;

/**
 * PromInstrumentCollector .
 *
 * @author liliangshan
 * @date 2021/8/13
 */
public class PromInstrumentCollector extends AbstractPromCollector implements Metric {

    protected final PromInstrument promInstrument;

    public PromInstrumentCollector(CollectorRegistry registry, PromInstrument promInstrument) {
        super(registry, promInstrument.getName(), promInstrument.getDescription(), promInstrument.getTags());
        this.promInstrument = promInstrument;
    }


    @Override
    protected List<PromContainer> collectInternal() {
        if (promInstrument == null || promInstrument.getCallable() == null) {
            return Lists.newArrayList();
        }
        try {
            return promInstrument.getCallable().call();
        } catch (Exception e) {
            return Lists.newArrayList();
        }
    }


}
