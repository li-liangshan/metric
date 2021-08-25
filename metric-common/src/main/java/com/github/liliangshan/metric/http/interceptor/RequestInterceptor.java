package com.github.liliangshan.metric.http.interceptor;

import okhttp3.Request;

/**
 * RequestInterceptor .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public interface RequestInterceptor extends Interceptor<Request, Request> {

    @Override
    default Integer order() {
        return -1;
    }

}
