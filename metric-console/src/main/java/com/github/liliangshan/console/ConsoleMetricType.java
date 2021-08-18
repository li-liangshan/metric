package com.github.liliangshan.console;

/**
 * ConsoleMetricType .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public enum ConsoleMetricType {

    UNKNOWN, // This is untyped in Prometheus text format.
    COUNTER,
    GAUGE,
    TIMER
}
