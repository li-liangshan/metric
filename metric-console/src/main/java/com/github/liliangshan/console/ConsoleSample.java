package com.github.liliangshan.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ConsoleSample .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class ConsoleSample {

    public final String name;
    public final List<String> tagNames;
    public final List<String> tagValues;
    public final Map<String, String> tags;
    public final double value;
    public final Long timestampMs;

    public ConsoleSample(String name, Map<String, String> tags, double value, Long timestampMs) {
        this.name = name;
        this.tags = tags;
        this.tagNames = new ArrayList<>(tags.keySet());
        this.tagValues = new ArrayList<>(tags.values());
        this.value = value;
        this.timestampMs = timestampMs;
    }

    public ConsoleSample(String name, Map<String, String> tags, double value) {
        this(name, tags, value, null);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConsoleSample)) {
            return false;
        } else {
            ConsoleSample other = (ConsoleSample)obj;
            return other.name.equals(this.name) && other.tagNames.equals(this.tagNames) && other.tagValues.equals(this.tagValues) && other.value == this.value && (this.timestampMs == null && other.timestampMs == null || other.timestampMs != null && other.timestampMs.equals(this.timestampMs));
        }
    }

    public int hashCode() {
        int hash = 1;
        hash = 37 * hash + this.name.hashCode();
        hash = 37 * hash + this.tagNames.hashCode();
        hash = 37 * hash + this.tagValues.hashCode();
        long d = Double.doubleToLongBits(this.value);
        hash = 37 * hash + (int)(d ^ d >>> 32);
        if (this.timestampMs != null) {
            hash = 37 * hash + this.timestampMs.hashCode();
        }

        return hash;
    }

    public String toString() {
        return "Name: " + this.name + " TagNames: " + this.tagNames + " TagValues: " + this.tagValues + " Value: " + this.value + " TimestampMs: " + this.timestampMs;
    }


}
