package com.github.liliangshan.console;

import java.util.List;

/**
 * ConsoleContainer .
 *
 * @author liliangshan
 * @date 2021/8/18
 */
public class ConsoleContainer {

    public final String name;
    public final String unit;
    public final ConsoleMetricType type;
    public final String description;
    public final List<ConsoleSample> samples;

    public ConsoleContainer(String name, String unit, ConsoleMetricType type, String description, List<ConsoleSample> samples) {
        this.name = name;
        this.unit = unit;
        this.type = type;
        this.description = description;
        this.samples = samples;
    }

    public ConsoleContainer(String name, ConsoleMetricType type, String description, List<ConsoleSample> samples) {
        this(name, "", type, description, samples);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConsoleContainer)) {
            return false;
        } else {
            ConsoleContainer other = (ConsoleContainer) obj;
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

    public ConsoleMetricType getType() {
        return type;
    }

    public List<ConsoleSample> getSamples() {
        return samples;
    }

}
