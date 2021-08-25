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


    public MetricRestClient() {
        this(RestClientConfig.DefaultConfig);
    }

    public MetricRestClient(RestClientConfig clientConfig) {
        super(clientConfig);
    }

}
