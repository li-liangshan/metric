package com.github.liliangshan.console.metric;

import com.github.liliangshan.console.ConsoleMetricType;
import com.github.liliangshan.metric.api.MtCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ConsoleCounter .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public class ConsoleCounter extends ConsoleMetric implements MtCounter {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleCounter.class);

    private final AtomicLong count = new AtomicLong(0);

    public ConsoleCounter(String name, String description, Map<String, String> tags) {
        super(name, description, tags);
    }

    @Override
    public void increment() {
        logger.info("metric name:{} , description: {}, tags: {}, count: {}",
                name, description, tags.toString(), count.incrementAndGet());
    }

    @Override
    public void increment(long delta) {
        logger.info("metric name:{} , description: {}, tags: {}, count: {}",
                name, description, tags.toString(), count.addAndGet(delta));
    }

    @Override
    public long count() {
        return count.get();
    }

    @Override
    protected ConsoleMetricType getConsoleMetricType() {
        return ConsoleMetricType.COUNTER;
    }

    @Override
    protected double getValue() {
        return count.get();
    }

}

