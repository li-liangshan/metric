package com.github.liliangshan.metric;

import java.io.IOException;

/**
 * MetricIOException .
 *
 * @author liliangshan
 * @date 2021/8/23
 */
public class MetricIOException extends IOException {

    private final String message;

    public MetricIOException(String message) {
        this(message, null);
    }

    public MetricIOException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public MetricIOException(Throwable cause) {
        this("", cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
