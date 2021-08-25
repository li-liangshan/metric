package com.github.liliangshan.metric;

import com.google.common.base.Objects;

import java.util.Map;
import java.util.Set;

/**
 * MetricId .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public class MetricId {

    private String name;
    private Set<String> tagKeys;

    public MetricId() {
    }

    public MetricId(String name, Set<String> tagKeys) {
        this.name = name;
        this.tagKeys = tagKeys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getTagKeys() {
        return tagKeys;
    }

    public void setTagKeys(Set<String> tagKeys) {
        this.tagKeys = tagKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetricId)) return false;
        MetricId metricID = (MetricId) o;
        return Objects.equal(name, metricID.name) && Objects.equal(tagKeys, metricID.tagKeys);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, tagKeys);
    }
}
