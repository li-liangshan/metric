package com.github.liliangshan.metric.api;

import java.util.concurrent.Callable;

/**
 * MtCollector .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public interface MtCollector extends Metric {

    void collect();

    Callable<?> getCallable();

}
