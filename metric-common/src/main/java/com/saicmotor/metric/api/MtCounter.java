package com.saicmotor.metric.api;

/**
 * MtCounter .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public interface MtCounter extends Metric {

    void increment();

    void increment(long delta);

    long count();

}
