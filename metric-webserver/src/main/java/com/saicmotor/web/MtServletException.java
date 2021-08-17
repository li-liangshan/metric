package com.saicmotor.web;

import com.saicmotor.metric.MetricException;

/**
 * MtServletException .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public class MtServletException extends MetricException {

    public MtServletException(String message) {
        super(message);
    }

    public MtServletException(String message, Throwable cause) {
        super(message, cause);
    }

    public MtServletException(Throwable cause) {
        super(cause);
    }

}
