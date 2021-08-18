package com.github.liliangshan.metric.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MetricRestClient .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public class MetricRestClient extends AbstractRestClient {

    private static final Logger logger = LoggerFactory.getLogger(MetricRestClient.class);


    private MetricRestClient() {
        super();
    }

    public static MetricRestClient getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final MetricRestClient INSTANCE = new MetricRestClient();
    }
}
