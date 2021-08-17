package com.saicmotor.metric.http;

import java.util.List;
import java.util.Map;

/**
 * RequestObject .
 *
 * @author liliangshan
 * @date 2021/8/17
 */
public class RequestObject {
    private String baseUrl;
    private List<String> pathSegments;
    private Map<String, String> queries;
    private HttpHeaders headers;

    public RequestObject() {

    }

    public RequestObject(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public RequestObject(String baseUrl, Map<String, String> queries) {
        this.baseUrl = baseUrl;
        this.queries = queries;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setPathSegments(List<String> pathSegments) {
        this.pathSegments = pathSegments;
    }

    public List<String> getPathSegments() {
        return pathSegments;
    }

    public Map<String, String> getQueries() {
        return queries;
    }

    public void setQueries(Map<String, String> queries) {
        this.queries = queries;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}
