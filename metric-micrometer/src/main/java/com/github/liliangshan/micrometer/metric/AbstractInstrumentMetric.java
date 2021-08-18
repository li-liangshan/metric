package com.github.liliangshan.micrometer.metric;

import com.github.liliangshan.metric.AbstractMetric;
import com.github.liliangshan.micrometer.Instrument;
import io.micrometer.core.instrument.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * AbstractInstrumentMetric .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public abstract class AbstractInstrumentMetric extends AbstractMetric {

    private final AtomicBoolean closed = new AtomicBoolean(false);

    protected AbstractInstrumentMetric(Instrument instrument) {
        super(instrument.getName(), instrument.getDescription(), instrument.getTags());
    }

    protected List<Tag> getTags(Map<String, String> tagMap) {
        return tagMap.entrySet().stream().map(entry -> Tag.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public abstract Meter.Id getMeterId();

    public abstract Iterable<Measurement> measure();

    public final void close() {
        if (closed.compareAndSet(false, true)) {
            this.closeInternal();
        }
    }

    protected abstract void closeInternal();

}
