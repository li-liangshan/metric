package com.saicmotor.metric.api;

import com.saicmotor.metric.MetricId;

import java.util.Map;

/**
 * Metric .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public interface Metric {

    MetricId getId();

    String getName();

    Map<String, String> getTagMap();

    String getDescription();

}
