package com.saicmotor.metric.prometheus;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * PrometheusObject .
 *
 * @author liliangshan
 * @date 2021/8/13
 */
public class PrometheusObject {

    private String namespace = "";
    private String subsystem = "";
    private String name = "";
    private String unit = "";
    private String help = "";
    private Callable<Double> callable;
    private Map<String, String> tags = Maps.newHashMap();

    public PrometheusObject() {

    }

    public PrometheusObject(String name, String help, Map<String, String> tags) {
        this(name, help, tags, null);
    }

    public PrometheusObject(String name, String help, Map<String, String> tags,
                            Callable<Double> callable) {
        this.name = name;
        this.help = help;
        this.tags = tags == null ? Maps.newHashMap() : tags;
        this.callable = callable;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public void setCallable(Callable<Double> callable) {
        this.callable = callable;
    }

    public Callable<Double> getCallable() {
        return callable;
    }
}
