package com.github.liliangshan.metric;

import com.google.common.base.Objects;

import java.util.Map;

/**
 * MetricId .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public class MetricId {

    private String name;
    private Map<String, String> tags;

    public MetricId() {
    }

    public MetricId(String name, Map<String, String> tags) {
        this.name = name;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetricId)) return false;
        MetricId metricID = (MetricId) o;
        return Objects.equal(name, metricID.name) && Objects.equal(tags.keySet(), metricID.tags.keySet());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, tags.keySet());
    }
}
