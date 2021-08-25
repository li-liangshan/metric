package com.github.liliangshan.metric.api;

import java.util.concurrent.Callable;

/**
 * MtGauge .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public interface MtGauge extends Metric {

    Callable<Double> getCallable();

    double updateAndGetValue();

}
