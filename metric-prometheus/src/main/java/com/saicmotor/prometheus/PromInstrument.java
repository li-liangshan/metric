package com.saicmotor.prometheus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * PromInstrument .
 *
 * @author liliangshan
 * @date 2021/8/13
 */
public class PromInstrument {

    private final String name;
    private final String description;
    private final Map<String, String> tags;
    private Callable<List<PromContainer>> callable;

    public PromInstrument(String name, String description, Map<String, String> tags) {
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public PromInstrument(String name, String description, Map<String, String> tags, Callable<List<PromContainer>> callable) {
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

    public Callable<List<PromContainer>> getCallable() {
        return callable;
    }

}
