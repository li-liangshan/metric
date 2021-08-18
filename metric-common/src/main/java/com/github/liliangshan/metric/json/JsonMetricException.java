package com.github.liliangshan.metric.json;

import com.github.liliangshan.metric.MetricException;

/**
 * JsonMetricException .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public class JsonMetricException extends MetricException {

    public JsonMetricException(String message) {
        super(message);
    }

    public JsonMetricException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonMetricException(Throwable cause) {
        super(cause);
    }

}
