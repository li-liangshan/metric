package com.github.liliangshan.metric.api;

import com.github.liliangshan.metric.MetricId;

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
