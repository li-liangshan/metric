package com.github.liliangshan.console.metric;

import com.google.common.collect.Lists;
import com.github.liliangshan.console.ConsoleContainer;
import com.github.liliangshan.console.ConsoleMetricType;
import com.github.liliangshan.console.ConsoleSample;
import com.github.liliangshan.console.registry.ConsoleRegistry;
import com.github.liliangshan.metric.AbstractMetric;

import java.util.List;
import java.util.Map;

/**
 * ConsoleMetric .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public abstract class ConsoleMetric extends AbstractMetric {

    protected ConsoleMetric(String name, String description, Map<String, String> tags) {
        super(name, description, tags);
    }

    @SuppressWarnings("unchecked")
    public <T extends ConsoleMetric> T register(ConsoleRegistry registry) {
        registry.register(this);
        return (T) this;
    }

    public List<ConsoleContainer> collect() {
        return Lists.newArrayList(new ConsoleContainer(
                this.getName(), this.getConsoleMetricType(), this.getDescription(),
                Lists.newArrayList(new ConsoleSample(this.getName(), this.getTagMap(), this.getValue()))
        ));
    }

    protected abstract ConsoleMetricType getConsoleMetricType();

    protected abstract double getValue();

}
