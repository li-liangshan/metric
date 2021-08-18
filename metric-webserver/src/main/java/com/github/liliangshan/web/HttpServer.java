package com.github.liliangshan.web;

import com.github.liliangshan.metric.servlet.AbstractMetricServlet;
import com.github.liliangshan.metric.servlet.MtServlet;
import com.github.liliangshan.web.servlet.HealthServlet;
import com.github.liliangshan.web.servlet.HomeServlet;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * HttpServer .
 *
 * @author liliangshan
 * @date 2021/8/14
 */
public class HttpServer implements WebServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
    private final Server server;
    private final ServletContextHandler contextHandler;
    private final String metricUrl;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final int port;


    public HttpServer(int port, AbstractMetricServlet metricServlet) {
        this.port = port;
        server = new Server(port);
        contextHandler = this.createContextHandler();
        server.setHandler(contextHandler);
        metricUrl = metricServlet.getMetricPathSpec();
        this.initializedServlet();
        contextHandler.addServlet(new ServletHolder(metricServlet), metricUrl);
    }

    private ServletContextHandler createContextHandler() {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/");
        return contextHandler;
    }

    private void initializedServlet() {
        contextHandler.addServlet(new ServletHolder(new HealthServlet()), "/health");
        contextHandler.addServlet(new ServletHolder(new HomeServlet()), "/");
    }

    @Override
    public void addServlet(MtServlet servlet) throws MtServletException {
        if (this.isRunning()) {
            throw new MtServletException("server is running, servlet must not be added.");
        }
        if (servlet == null || StringUtils.isBlank(servlet.getPathSpec())) {
            throw new MtServletException("servlet path spec must not be null.");
        }
        logger.info("add servlet path:{}", servlet.getPathSpec());
        contextHandler.addServlet(new ServletHolder(servlet), servlet.getPathSpec());
    }

    @Override
    public void addServlets(List<MtServlet> servlets) throws MtServletException {
        if (this.isRunning()) {
            throw new MtServletException("server is running, servlet must not be added");
        }
        if (servlets == null) {
            return;
        }
        servlets.forEach(this::addServlet);
    }

    @Override
    public boolean isRunning() {
        return running.get() && server.isRunning();
    }

    @Override
    public void run() {
        try {
            if (running.compareAndSet(false, true)) {
                if (server.isStarting() || server.isStarted()) {
                    logger.info("http server have been starting or started.");
                    return;
                }
                logger.info("http server is starting.");
                server.start();
                logger.info("http server is running in port:{}, metrics path:{}", this.port, this.metricUrl);
                server.join();
            }
        } catch (Exception e) {
            System.exit(1);
        }
    }

    @Override
    public void close() {
        if (running.compareAndSet(true, false)) {
            if (server.isStopped() || server.isStopping()) {
                logger.info("http server have been stopping or stopped.");
                return;
            }
            try {
                logger.info("http server is stopping.");
                server.stop();
            } catch (Exception e) {
                running.set(server.isRunning());
            }
        }
    }

}
