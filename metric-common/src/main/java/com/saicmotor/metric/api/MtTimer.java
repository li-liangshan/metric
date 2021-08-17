package com.saicmotor.metric.api;

import java.util.concurrent.TimeUnit;

/**
 * MtTimer .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public interface MtTimer extends Metric {

    void record(long timeMillis);

    void record(long time, TimeUnit unit);

}
