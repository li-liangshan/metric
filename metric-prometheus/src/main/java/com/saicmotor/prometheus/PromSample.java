package com.saicmotor.prometheus;

import java.util.List;

/**
 * PromSample .
 *
 * @author liliangshan
 * @date 2021/8/13
 */
public class PromSample {

    public final String name;
    public final List<String> labelNames;
    public final List<String> labelValues;
    public final double value;
    public final Long timestampMs;


    public PromSample(String name, List<String> labelNames, List<String> labelValues, double value, Long timestampMs) {
        this.name = name;
        this.labelNames = labelNames;
        this.labelValues = labelValues;
        this.value = value;
        this.timestampMs = timestampMs;
    }

    public PromSample(String name, List<String> labelNames, List<String> labelValues, double value) {
        this(name, labelNames, labelValues, value, null);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PromSample)) {
            return false;
        } else {
            PromSample other = (PromSample) obj;
            return other.name.equals(this.name) && other.labelNames.equals(this.labelNames) && other.labelValues.equals(this.labelValues) && other.value == this.value && (this.timestampMs == null && other.timestampMs == null || other.timestampMs != null && other.timestampMs.equals(this.timestampMs));
        }
    }

    public int hashCode() {
        int hash = 1;
        hash = 37 * hash + this.name.hashCode();
        hash = 37 * hash + this.labelNames.hashCode();
        hash = 37 * hash + this.labelValues.hashCode();
        long d = Double.doubleToLongBits(this.value);
        hash = 37 * hash + (int) (d ^ d >>> 32);
        if (this.timestampMs != null) {
            hash = 37 * hash + this.timestampMs.hashCode();
        }

        return hash;
    }

    public String toString() {
        return "Name: " + this.name + " LabelNames: " + this.labelNames + " labelValues: " + this.labelValues + " Value: " + this.value + " TimestampMs: " + this.timestampMs;
    }

}
