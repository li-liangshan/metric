package com.github.liliangshan.console.registry;

import com.github.liliangshan.console.ConsoleContainer;
import com.github.liliangshan.console.format.ConsoleFormat;
import com.github.liliangshan.console.metric.ConsoleMetric;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * ConsoleRegistry .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class ConsoleRegistry {

    private final Object lock;
    private final Map<ConsoleMetric, List<String>> consoleMetricsToNames;
    private final Map<String, ConsoleMetric> namesToConsoleMetrics;

    public ConsoleRegistry() {
        this.lock = new Object();
        this.consoleMetricsToNames = new HashMap<>();
        this.namesToConsoleMetrics = new HashMap<>();
    }

    public void register(ConsoleMetric consoleMetric) {
        List<String> names = this.consoleMetricsToNames(consoleMetric);
        synchronized (this.lock) {
            Iterator<String> iterator = names.iterator();
            String name;
            while (iterator.hasNext()) {
                name = iterator.next();
                if (this.namesToConsoleMetrics.containsKey(name)) {
                    throw new IllegalArgumentException("Console Metric already registered that provides name: " + name);
                }
            }
            iterator = names.iterator();
            while (iterator.hasNext()) {
                name = iterator.next();
                this.namesToConsoleMetrics.put(name, consoleMetric);
            }
            this.consoleMetricsToNames.put(consoleMetric, names);
        }
    }

    public void unregister(ConsoleMetric consoleMetric) {
        synchronized (lock) {
            List<String> names = consoleMetricsToNames.remove(consoleMetric);
            for (String name : names) {
                namesToConsoleMetrics.remove(name);
            }
        }
    }

    public void clear() {
        synchronized (lock) {
            consoleMetricsToNames.clear();
            namesToConsoleMetrics.clear();
        }
    }

    Set<ConsoleMetric> consoleMetrics() {
        return new HashSet<>(consoleMetricsToNames.keySet());
    }

    private Enumeration<ConsoleContainer> consoleContainerEnumeration() {
        return new ConsoleContainerEnumeration(this);
    }

    private List<String> consoleMetricsToNames(ConsoleMetric consoleMetric) {
        List<ConsoleContainer> containers = consoleMetric.collect();
        List<String> names = new ArrayList<>();
        for (ConsoleContainer family : containers) {
            switch (family.type) {
                case COUNTER:
                    names.add(family.name + "_total");
                    names.add(family.name + "_created");
                    names.add(family.name);
                    break;
                case TIMER:
                    names.add(family.name + "_count");
                    names.add(family.name + "_sum");
                    names.add(family.name + "_created");
                    names.add(family.name);
                    break;
                default:
                    names.add(family.name);
            }
        }
        return names;
    }

    public void scrape(Writer writer) throws IOException {
        ConsoleFormat.write(writer, this.consoleContainerEnumeration());
    }

}
