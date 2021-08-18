package com.github.liliangshan.prometheus.collector;

import com.github.liliangshan.prometheus.PromContainer;
import com.github.liliangshan.prometheus.PromInstrument;
import com.google.common.collect.Lists;
import com.github.liliangshan.metric.api.Metric;
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
