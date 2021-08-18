package com.github.liliangshan.console.registry;

import com.github.liliangshan.console.ConsoleContainer;
import com.github.liliangshan.console.metric.ConsoleMetric;

import java.util.*;

/**
 * ConsoleContainerEnumeration .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
class ConsoleContainerEnumeration implements Enumeration<ConsoleContainer> {

    private final Iterator<ConsoleMetric> consoleMetricIterator;
    private Iterator<ConsoleContainer> metricFamilySamples;
    private ConsoleContainer next;

    ConsoleContainerEnumeration(ConsoleRegistry consoleRegistry) {
        consoleMetricIterator = consoleRegistry.consoleMetrics().iterator();
        findNextElement();
    }

    private void findNextElement() {
        next = null;

        while (metricFamilySamples != null && metricFamilySamples.hasNext()) {
            next = metricFamilySamples.next();
            if (next != null) {
                return;
            }
        }

        while (consoleMetricIterator.hasNext()) {
            metricFamilySamples = consoleMetricIterator.next().collect().iterator();
            while (metricFamilySamples.hasNext()) {
                next = metricFamilySamples.next();
                if (next != null) {
                    return;
                }
            }
        }
    }

    @Override
    public boolean hasMoreElements() {
        return next != null;
    }

    @Override
    public ConsoleContainer nextElement() {
        ConsoleContainer current = next;
        if (current == null) {
            throw new NoSuchElementException();
        }
        findNextElement();
        return current;
    }

}
