package com.saicmotor.prometheus;

import io.prometheus.client.Collector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * PromContainer .
 *
 * @author liliangshan
 * @date 2021/8/13
 */
public class PromContainer {
    private final String name;
    private final String description;
    public final String unit;
    public final Collector.Type type = Collector.Type.UNKNOWN;
    public final List<PromSample> samples;

    public PromContainer(String name, String unit, String description, List<PromSample> samples) {
        this.name = name;
        this.unit = unit;
        this.description = description;
        this.samples = samples;
    }

    public PromContainer(String name, String description, List<PromSample> samples) {
        this(name, "", description, samples);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PromContainer)) {
            return false;
        } else {
            PromContainer other = (PromContainer) obj;
            return other.name.equals(this.name)
                    && other.unit.equals(this.unit)
                    && other.type.equals(this.type)
                    && other.description.equals(this.description)
                    && other.samples.equals(this.samples);
        }
    }

    public int hashCode() {
        int hash = 1;
        hash = 37 * hash + this.name.hashCode();
        hash = 37 * hash + this.unit.hashCode();
        hash = 37 * hash + this.type.hashCode();
        hash = 37 * hash + this.description.hashCode();
        hash = 37 * hash + this.samples.hashCode();
        return hash;
    }

    public String toString() {
        return "Name: " + this.name + " Unit:" + this.unit + " Type: " + this.type + " Description: " + this.description + " Samples: " + this.samples;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public Collector.Type getType() {
        return type;
    }

    public List<PromSample> getSamples() {
        return samples;
    }
}
