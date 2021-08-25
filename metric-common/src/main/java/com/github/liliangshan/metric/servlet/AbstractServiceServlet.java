package com.github.liliangshan.metric.servlet;


import com.github.liliangshan.metric.client.AbstractClient;
import com.github.liliangshan.metric.client.Client;
import com.github.liliangshan.metric.service.AbstractHttpService;
import com.github.liliangshan.metric.service.HttpService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AbstractClientHttpServlet .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public abstract class AbstractServiceServlet extends AbstractHttpServlet implements MtServlet {

    protected final HttpService httpService;
    protected final Client client;

    protected AbstractServiceServlet(AbstractHttpService httpService, AbstractClient client) {
        super();
        this.httpService = httpService;
        this.client = client;
        logger.info(this.getClass().getSimpleName() + "created");
    }

    public HttpService getHttpService() {
        return httpService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (httpService == null) {
            super.doGet(req, resp);
            return;
        }
        httpService.httpGet(client, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (httpService == null) {
            super.doPost(req, resp);
            return;
        }
        httpService.httpPost(client, req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (httpService == null) {
            super.doPut(req, resp);
            return;
        }
        httpService.httpPut(client, req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (httpService == null) {
            super.doDelete(req, resp);
            return;
        }
        httpService.httpDelete(client, req, resp);
    }

}
