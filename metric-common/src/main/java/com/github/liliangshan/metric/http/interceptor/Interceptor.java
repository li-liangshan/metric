package com.github.liliangshan.metric.http.interceptor;


import org.jetbrains.annotations.NotNull;

/**
 * Interceptor .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public interface Interceptor<I, R> extends Comparable<Interceptor<I, R>> {

    Integer order();

    R intercept(I object);

    @Override
    default int compareTo(@NotNull Interceptor<I, R> o) {
        return this.order().compareTo(o.order());
    }

}
