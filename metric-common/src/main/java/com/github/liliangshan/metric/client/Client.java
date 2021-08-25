package com.github.liliangshan.metric.client;

import com.github.liliangshan.metric.api.MtCollector;
import com.github.liliangshan.metric.api.MtCounter;
import com.github.liliangshan.metric.api.MtGauge;
import com.github.liliangshan.metric.api.MtTimer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Client .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public interface Client {

    MtCounter counter(String metricsName, String description, Map<String, String> tags);

    MtGauge gauge(String metricsName, String description, Map<String, String> tags, Callable<Double> callable);

    MtTimer timer(String metricsName, String description, Map<String, String> tags);

    MtCollector collector(String name, String description, Map<String, String> tags, Callable<?> callable);

    void destroy();

    void updateMetricStat();

}
