package com.saicmotor.metric.client;

import com.saicmotor.metric.api.MtCounter;
import com.saicmotor.metric.api.MtGauge;
import com.saicmotor.metric.api.MtTimer;

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

    void destroy();

}
