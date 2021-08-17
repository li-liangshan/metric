package com.saicmotor.metric.servlet;

import javax.servlet.Servlet;

/**
 * MtServlet .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public interface MtServlet extends Servlet {

    String getPathSpec();

}
