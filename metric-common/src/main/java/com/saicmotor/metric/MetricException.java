package com.saicmotor.metric;

/**
 * MetricException .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public class MetricException extends RuntimeException {

    public MetricException(String message) {
        super(message);
    }

    public MetricException(String message, Throwable cause) {
        super(message, cause);
    }

    public MetricException(Throwable cause) {
        super(cause);
    }
    
}
