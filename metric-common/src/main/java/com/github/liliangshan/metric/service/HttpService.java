package com.github.liliangshan.metric.service;

import com.github.liliangshan.metric.MetricIOException;
import com.github.liliangshan.metric.client.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpService .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public interface HttpService extends Service {

    void httpGet(Client client, HttpServletRequest req, HttpServletResponse resp) throws MetricIOException;

    void httpPost(Client client, HttpServletRequest req, HttpServletResponse resp) throws MetricIOException;

    void httpPut(Client client, HttpServletRequest req, HttpServletResponse resp) throws MetricIOException;

    void httpDelete(Client client, HttpServletRequest req, HttpServletResponse resp) throws MetricIOException;

}
