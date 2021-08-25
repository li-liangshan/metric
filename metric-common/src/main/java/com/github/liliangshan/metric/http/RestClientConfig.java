package com.github.liliangshan.metric.http;

/**
 * RestClientConfig .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public class RestClientConfig {

    public static final RestClientConfig DefaultConfig = new RestClientConfig();

    private long connectTimeoutMs = 30;
    private long readTimeoutMs = 30;
    private long writeTimeoutMs = 30;


    public void setConnectTimeoutMs(long connectTimeoutMs) {
        this.connectTimeoutMs = connectTimeoutMs;
    }

    public long getConnectTimeoutMs() {
        return connectTimeoutMs;
    }

    public void setReadTimeoutMs(long readTimeoutMs) {
        this.readTimeoutMs = readTimeoutMs;
    }

    public long getReadTimeoutMs() {
        return readTimeoutMs;
    }

    public void setWriteTimeoutMs(long writeTimeoutMs) {
        this.writeTimeoutMs = writeTimeoutMs;
    }

    public long getWriteTimeoutMs() {
        return writeTimeoutMs;
    }
}
