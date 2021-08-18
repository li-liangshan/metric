package com.github.liliangshan.metric.http;

import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * HttpHeader .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public class HttpHeader {

    private String key;
    private String value;

    public HttpHeader() {

    }

    public HttpHeader(String key, String value) {
        this.key = StringUtils.trimToEmpty(key);
        this.value = StringUtils.trimToEmpty(value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = StringUtils.trimToEmpty(key);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = StringUtils.trimToEmpty(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpHeader)) return false;
        HttpHeader that = (HttpHeader) o;
        return Objects.equal(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }

}
