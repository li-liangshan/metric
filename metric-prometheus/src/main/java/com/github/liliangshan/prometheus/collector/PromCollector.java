package com.github.liliangshan.prometheus.collector;

import com.github.liliangshan.metric.api.MtCollector;
import com.github.liliangshan.prometheus.PromContainer;

import java.util.List;

/**
 * PromCollector .
 *
 * @author liliangshan
 * @date 2021/8/13
 */
public interface PromCollector extends MtCollector {

    List<PromContainer> getValues();

}
