package com.github.liliangshan.prometheus.client;

import com.github.liliangshan.metric.client.Client;
import com.github.liliangshan.prometheus.PromContainer;
import com.github.liliangshan.prometheus.collector.PromCollector;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * PrometheusInstrumentClient .
 *
 * @author liliangshan
 * @date 2021/8/13
 */
public interface PrometheusInstrumentClient extends Client {

    PromCollector collector(String name, String description, Map<String, String> tags,
                            Callable<List<PromContainer>> callable);

}
