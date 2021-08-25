package com.github.liliangshan.metric.service;

import com.github.liliangshan.metric.MetricException;
import com.github.liliangshan.metric.client.Client;

/**
 * Service .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public interface Service {

    Object execute(Client client) throws MetricException;

}
