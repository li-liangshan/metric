package com.github.liliangshan.metric.http;

import java.io.IOException;

/**
 * HttpIOException .
 *
 * @author liliangshan
 * @date 2021/8/17
 */
public class HttpIOException extends IOException {

    public HttpIOException(String message) {
        super(message);
    }

    public HttpIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpIOException(Throwable cause) {
        super(cause);
    }
}
