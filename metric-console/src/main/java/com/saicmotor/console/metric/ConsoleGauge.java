package com.saicmotor.console.metric;

import com.saicmotor.metric.AbstractMetric;
import com.saicmotor.metric.api.MtGauge;
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
public class ConsoleGauge extends AbstractMetric implements MtGauge {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleGauge.class);

    private final ScheduledExecutorService executor;
    private final Callable<Double> callable;

    public ConsoleGauge(long period, String name, String description, Map<String, String> tags, Callable<Double> callable) {
        super(name, description, tags);
        this.callable = callable;
        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> {
            logger.info("metric name:{} , description: {}, tags: {}, gauge: {}",
                    name, description, tags.toString(), value());
        }, 0, period, TimeUnit.MILLISECONDS);
    }

    @Override
    public Callable<Double> getCallable() {
        return callable;
    }

    @Override
    public double value() {
        try {
            return getCallable().call();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

}
