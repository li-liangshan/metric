package com.github.liliangshan.console.metric;

import com.google.common.collect.Maps;
import com.github.liliangshan.console.ConsoleMetricType;
import com.github.liliangshan.metric.api.MtTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ConsoleTimer .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public class ConsoleTimer extends ConsoleMetric implements MtTimer {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleTimer.class);

    private final long limit;
    private Map<Long, Long> store = Maps.newHashMap();

    public ConsoleTimer(long limit, String name, String description, Map<String, String> tags) {
        super(name, description, tags);
        this.limit = limit;
    }

    @Override
    public void record(long timeMillis) {
        if (store.size() >= limit) {
            store = Maps.newHashMap();
        }
        store.put(System.currentTimeMillis(), timeMillis);
        logger.info("time consumed: {} ms.", timeMillis);
    }

    @Override
    public void record(long time, TimeUnit unit) {
        this.record(TimeUnit.MILLISECONDS.convert(time, unit));
    }

    @Override
    protected ConsoleMetricType getConsoleMetricType() {
        return ConsoleMetricType.TIMER;
    }

    @Override
    protected double getValue() {
        return store.keySet().size();
    }

    @Override
    public void record(Runnable runnable) {

    }

}
