package com.github.liliangshan.samples.prometheus.servlet;


import com.github.liliangshan.metric.client.AbstractClient;
import com.github.liliangshan.metric.servlet.AbstractServiceServlet;
import com.github.liliangshan.samples.prometheus.service.PingService;

/**
 * PingServlet .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public class PingServlet extends AbstractServiceServlet {

    private static final PingService DefaultPingService = new PingService();

    public PingServlet(AbstractClient client) {
        this(DefaultPingService, client);
    }

    public PingServlet(PingService service, AbstractClient client) {
        super(service, client);
    }

    @Override
    public String getPathSpec() {
        return "/ping";
    }


}
