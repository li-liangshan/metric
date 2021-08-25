package com.github.liliangshan.metric.http.interceptor;

import okhttp3.Response;

/**
 * ResponseInterceptor .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public interface ResponseInterceptor extends Interceptor<Response, Response> {

    @Override
    default Integer order() {
        return -1;
    }

}
