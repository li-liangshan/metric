package com.github.liliangshan.prometheus.collector;

import com.github.liliangshan.metric.AbstractMetric;
import com.github.liliangshan.prometheus.PromContainer;
import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * AbstractPromCollector .
 *
 * @author liliangshan
 * @date 2021/8/12
 */
public abstract class AbstractPromCollector extends AbstractMetric implements PromCollector {

    private final Collector collector = new Collector() {
        @Override
        public List<MetricFamilySamples> collect() {
            List<PromContainer> containers = collectInternal();
            return containers.stream().map(container -> {
                List<MetricFamilySamples.Sample> samples = container.samples.stream()
                        .map(sample -> new MetricFamilySamples.Sample(sample.name, sample.labelNames, sample.labelValues, sample.value))
                        .collect(Collectors.toList());
                return new MetricFamilySamples(container.getName(),
                        container.getUnit(), container.getType(), container.getDescription(), samples);
            }).collect(Collectors.toList());
        }
    };

    protected AbstractPromCollector(CollectorRegistry registry, String name, String description, Map<String, String> tags) {
        super(name, description, tags);
        collector.register(registry);
    }

    protected abstract List<PromContainer> collectInternal();

    @Override
    final public void collect() {
        collector.collect();
    }

}
