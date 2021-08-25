package com.github.liliangshan.metric.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;

/**
 * AbstractHttpServlet .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public abstract class AbstractHttpServlet extends HttpServlet implements MtServlet {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected AbstractHttpServlet() {
    }

    public Logger getLogger() {
        return logger;
    }

}
