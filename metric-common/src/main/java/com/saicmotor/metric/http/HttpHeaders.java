package com.saicmotor.metric.http;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * HttpHeaders .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public class HttpHeaders {

    private final Set<HttpHeader> headers = Sets.newHashSet();

    public Collection<HttpHeader> getHeaders() {
        return headers;
    }

    public void addHeader(HttpHeader header) {
        if (header != null) {
            this.headers.add(header);
        }
    }

    public void addHeader(String key, String value) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        this.addHeader(new HttpHeader(key, value));
    }

    public void addHeaders(List<HttpHeader> headers) {
        if (headers != null) {
            this.headers.addAll(headers);
        }
    }

    public void addHeaders(Map<String, String> map) {
        if (map != null) {
            map.forEach((key, value) -> this.addHeader(new HttpHeader(key, value)));
        }
    }

    public Map<String, String> toMap() {
        Map<String, String> map = Maps.newHashMap();
        headers.forEach(it -> map.put(it.getKey(), it.getValue()));
        return map;
    }

    public static HttpHeaders of(Map<String, String> map) {
        HttpHeaders headers = new HttpHeaders();
        if (map == null) {
            return headers;
        }
        headers.addHeaders(map);
        return headers;
    }

    public static HttpHeaders of(String key, String value) {
        HttpHeaders headers = new HttpHeaders();
        if (StringUtils.isBlank(key)) {
            return headers;
        }
        headers.addHeader(key, value);
        return headers;
    }
}
