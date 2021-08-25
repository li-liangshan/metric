package com.github.liliangshan.metric.service;

import com.github.liliangshan.metric.MetricException;
import com.github.liliangshan.metric.MetricIOException;
import com.github.liliangshan.metric.client.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AbstractHttpService .
 *
 * @author liliangshan
 * @date 2021/8/24
 */
public abstract class AbstractHttpService implements HttpService {

    protected AbstractHttpService() {
    }


    @Override
    public void httpGet(Client client, HttpServletRequest req, HttpServletResponse resp) throws MetricIOException {
        throw new MetricIOException("not implemented http get");
    }

    @Override
    public void httpPost(Client client, HttpServletRequest req, HttpServletResponse resp) throws MetricIOException {
        throw new MetricIOException("not implemented http post");
    }

    @Override
    public void httpPut(Client client, HttpServletRequest req, HttpServletResponse resp) throws MetricIOException {
        throw new MetricIOException("not implemented http put");
    }

    @Override
    public void httpDelete(Client client, HttpServletRequest req, HttpServletResponse resp) throws MetricIOException {
        throw new MetricIOException("not implemented http delete");
    }

    @Override
    public Object execute(Client client) throws MetricException {
        return null;
    }

}
