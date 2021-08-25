package com.github.liliangshan.metric;

import com.github.liliangshan.metric.api.Metric;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * AbstractMetric .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public abstract class AbstractMetric implements Metric {

    protected String name;
    protected String description;
    protected Map<String, String> tags;

    protected AbstractMetric(String name, String description, Map<String, String> tags) {
        this.name = StringUtils.trimToEmpty(name);
        this.description = StringUtils.trimToEmpty(description);
        this.tags = tags == null ? Collections.emptyMap() : tags;
    }

    @Override
    public final MetricId getId() {
        return new MetricId(name, tags.keySet());
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Map<String, String> getTagMap() {
        return tags;
    }

    protected List<String> getTagNames() {
        return new ArrayList<>(tags.keySet());
    }

    protected List<String> getTagValues() {
        return new ArrayList<>(tags.values());
    }

    @Override
    public final String getDescription() {
        return description;
    }

}
