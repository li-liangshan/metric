package com.saicmotor.web;

import com.saicmotor.metric.servlet.MtServlet;
import com.saicmotor.web.servlet.HealthServlet;
import com.saicmotor.web.servlet.HomeServlet;
import io.prometheus.client.exporter.MetricsServlet;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * HttpServer .
 *
 * @author liliangshan
 * @date 2021/8/14
 */
public class HttpServer implements WebServer {

    private final Server server;
    private final ServletContextHandler contextHandler;
    private final AtomicBoolean running = new AtomicBoolean(false);


    public HttpServer(int port) {
        server = new Server(port);
        contextHandler = this.createContextHandler();
        server.setHandler(contextHandler);
        this.initializedServlet();
    }

    private ServletContextHandler createContextHandler() {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/");
        return contextHandler;
    }

    private void initializedServlet() {
        contextHandler.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
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
                    return;
                }
                server.start();
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
                return;
            }
            try {
                server.stop();
            } catch (Exception e) {
                running.set(server.isRunning());
            }
        }
    }

}
