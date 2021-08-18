package com.github.liliangshan.metric.servlet;

import com.github.liliangshan.metric.client.AbstractClient;
import com.github.liliangshan.metric.client.Client;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MetricServlet .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public abstract class AbstractMetricServlet extends AbstractHttpServlet implements MtServlet {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final Client client;

    protected AbstractMetricServlet(AbstractClient client) {
        this.client = client;
    }

    @Override
    final public String getPathSpec() {
        return "/metrics";
    }

    protected String getMetricPatchSpecPrefix() {
        return "";
    }

    final public String getMetricPathSpec() {
        String prefix = StringUtils.trimToEmpty(getMetricPatchSpecPrefix());
        if (prefix.equals("/") || prefix.equals("")) {
            return this.getPathSpec();
        }
        return prefix + getPathSpec();
    }
}
