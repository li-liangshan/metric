package com.saicmotor.console.format;


import com.saicmotor.console.ConsoleContainer;
import com.saicmotor.console.ConsoleMetricType;
import io.prometheus.client.Collector;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ConsoleFormat .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class ConsoleFormat {

    public static void write(Writer writer, Enumeration<ConsoleContainer> enumeration) throws IOException {
        TextFormat.write004(writer, new Enumeration<Collector.MetricFamilySamples>() {
            @Override
            public boolean hasMoreElements() {
                return enumeration.hasMoreElements();
            }

            @Override
            public Collector.MetricFamilySamples nextElement() {
                ConsoleContainer container = enumeration.nextElement();
                List<Collector.MetricFamilySamples.Sample> samples = container.samples.stream()
                        .map(sample -> new Collector.MetricFamilySamples.Sample(sample.name, sample.tagNames, sample.tagValues, sample.value))
                        .collect(Collectors.toList());
                return new Collector.MetricFamilySamples(container.getName(),
                        container.getUnit(), toPrometheusType(container.getType()), container.getDescription(), samples);
            }
        });
    }

    private static Collector.Type toPrometheusType(ConsoleMetricType type) {
        switch (type) {
            case COUNTER:
                return Collector.Type.COUNTER;
            case GAUGE:
                return Collector.Type.GAUGE;
            case TIMER:
                return Collector.Type.UNKNOWN;
        }
        return Collector.Type.UNKNOWN;
    }

}
