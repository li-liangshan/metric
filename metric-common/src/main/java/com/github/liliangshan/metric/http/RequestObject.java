package com.github.liliangshan.metric.http;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

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

    private RequestObject(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.pathSegments = builder.pathSegments;
        this.queries = builder.queries;
        this.headers = builder.headers;
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

    public void setQueries(Map<String, String> queries) {
        this.queries = queries;
    }

    public Map<String, String> getQueries() {
        return queries;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public static class Builder {
        private String baseUrl;
        private final List<String> pathSegments = Lists.newArrayList();
        private final Map<String, String> queries = Maps.newHashMap();
        private HttpHeaders headers = new HttpHeaders();

        public Builder() {

        }

        public Builder(RequestObject object) {
            this.baseUrl = object.baseUrl;
            this.pathSegments.addAll(object.pathSegments);
            this.queries.putAll(object.queries);
            this.headers = HttpHeaders.of(object.getHeaders().toMap());
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder pathSegment(String pathSegment) {
            this.pathSegments.add(StringUtils.trim(pathSegment));
            return this;
        }

        public Builder pathSegments(List<String> pathSegments) {
            this.pathSegments.addAll(pathSegments);
            return this;
        }

        public Builder query(String key, String value) {
            if (!StringUtils.isBlank(key)) {
                this.queries.put(StringUtils.trim(key), StringUtils.trim(value));
            }
            return this;
        }

        public Builder queries(Map<String, String> queries) {
            if (queries != null) {
                this.queries.putAll(queries);
            }
            return this;
        }

        public Builder httpHeader(HttpHeader header) {
            this.headers.addHeader(header);
            return this;
        }

        public Builder httpHeaders(List<HttpHeader> headers) {
            this.headers.addHeaders(headers);
            return this;
        }

        public Builder httpHeaders(HttpHeaders headers) {
            if (headers != null) {
                this.headers = headers;
            }
            return this;
        }

        public RequestObject build() {
            return new RequestObject(this);
        }
    }
}
