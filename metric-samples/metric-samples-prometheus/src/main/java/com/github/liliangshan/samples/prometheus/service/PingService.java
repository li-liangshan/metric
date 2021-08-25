package com.github.liliangshan.samples.prometheus.service;

import com.github.liliangshan.metric.MetricException;
import com.github.liliangshan.metric.MetricIOException;
import com.github.liliangshan.metric.api.MtCounter;
import com.github.liliangshan.metric.client.Client;
import com.github.liliangshan.metric.service.AbstractHttpService;
import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PingService .
 *
 * @author liliangshan
 * @date 2021/8/23
 */
public class PingService extends AbstractHttpService {

    @Override
    public void httpGet(Client client, HttpServletRequest req, HttpServletResponse resp) throws MetricIOException {
        try {
            resp.getWriter().write((String) this.execute(client));
            resp.setStatus(200);
        } catch (IOException e) {
            resp.setStatus(500);
            throw new MetricIOException(e.getMessage(), e);
        }
    }

    @Override
    public void httpPost(Client client, HttpServletRequest req, HttpServletResponse resp) throws MetricIOException {
        try {
            resp.getWriter().write((String) this.execute(client));
            resp.setStatus(200);
        } catch (IOException e) {
            resp.setStatus(500);
            throw new MetricIOException(e.getMessage(), e);
        }
    }

    @Override
    public Object execute(Client client) throws MetricException {
        MtCounter counter = client.counter("ping_request", "exporter ping request",
                Maps.newHashMap());
        counter.increment();
        return "pong";
    }

}
