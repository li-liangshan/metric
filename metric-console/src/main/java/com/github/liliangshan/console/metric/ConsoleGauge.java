package com.github.liliangshan.console.metric;

import com.github.liliangshan.console.ConsoleMetricType;
import com.github.liliangshan.metric.api.MtGauge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ConsoleGauge .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public class ConsoleGauge extends ConsoleMetric implements MtGauge {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleGauge.class);

    private final ScheduledExecutorService executor;
    private final Callable<Double> callable;

    public ConsoleGauge(long period, String name, String description, Map<String, String> tags, Callable<Double> callable) {
        super(name, description, tags);
        this.callable = callable;
        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> {
            logger.info("metric name:{} , description: {}, tags: {}, gauge: {}",
                    name, description, tags.toString(), updateAndGetValue());
        }, 0, period, TimeUnit.MILLISECONDS);
    }

    @Override
    public Callable<Double> getCallable() {
        return callable;
    }

    @Override
    public double updateAndGetValue() {
        try {
            return getCallable().call();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    protected ConsoleMetricType getConsoleMetricType() {
        return ConsoleMetricType.GAUGE;
    }

    @Override
    protected double getValue() {
        return this.updateAndGetValue();
    }

}
