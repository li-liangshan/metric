package com.saicmotor.micrometer;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Instrument .
 *
 * @author liliangshan
 * @date 2021/8/11
 */
public class Instrument {

    private String name;
    private String description;
    private Map<String, String> tags;
    private Callable<Double> callable;

    public Instrument(String name, String description, Map<String, String> tags) {
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public Instrument(String name, String description, Map<String, String> tags, Callable<Double> callable) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.callable = callable;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public Callable<Double> getCallable() {
        return callable;
    }

}
