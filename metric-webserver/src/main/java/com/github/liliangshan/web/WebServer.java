package com.github.liliangshan.web;

import com.github.liliangshan.metric.servlet.MtServlet;

import java.util.List;

/**
 * WebServer .
 *
 * @author liliangshan
 * @date 2021/8/14
 */
public interface WebServer {

    void addServlet(MtServlet servlet) throws MtServletException;

    void addServlets(List<MtServlet> servlets) throws MtServletException;

    void run();

    boolean isRunning();

    void close();

}
