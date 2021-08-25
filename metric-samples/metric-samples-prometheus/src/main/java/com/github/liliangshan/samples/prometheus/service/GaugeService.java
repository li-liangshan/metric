package com.github.liliangshan.samples.prometheus.service;

import com.github.liliangshan.metric.MetricException;
import com.github.liliangshan.metric.api.MtGauge;
import com.github.liliangshan.metric.client.Client;
import com.github.liliangshan.metric.service.Service;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Random;

/**
 * GaugeService .
 *
 * @author liliangshan
 * @date 2021/8/23
 */
public class GaugeService implements Service {

    private final Random random = new Random();

    @Override
    public Object execute(Client client) throws MetricException {
        MtGauge gauge = client.gauge("request_mem", "this is request mem",
                Maps.asMap(Sets.newHashSet("tag1", "tag2"), new Function<String, String>() {
                    @Override
                    public @Nullable String apply(@Nullable String s) {
                        return s + "_value";
                    }
                }), random::nextDouble); // 计量仪-- cpu()
        return gauge.updateAndGetValue();
    }
}
