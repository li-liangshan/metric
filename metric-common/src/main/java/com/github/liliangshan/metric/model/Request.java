package com.github.liliangshan.metric.model;

import java.util.Map;

/**
 * Request .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public class Request {

    private String contextPath;
    private Map<String, String[]> queryParameterMap;
    private String body;
    public long contentLengthLong;
    public String contentType;

    private Map<String, String> headerMap;


}
