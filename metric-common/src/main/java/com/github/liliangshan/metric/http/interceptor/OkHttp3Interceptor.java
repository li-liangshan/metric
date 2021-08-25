package com.github.liliangshan.metric.http.interceptor;

import com.github.liliangshan.metric.http.AbstractRestClient;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * OkHttp3Interceptor .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public final class OkHttp3Interceptor implements Interceptor {

    private final AbstractRestClient restClient;

    public OkHttp3Interceptor(AbstractRestClient restClient) {
        this.restClient = restClient;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        AtomicReference<Request> request = new AtomicReference<>(chain.request());
        Set<RequestInterceptor> requestInterceptors = restClient.getRequestInterceptors();
        requestInterceptors.forEach(it -> request.set(it.intercept(request.get())));
        AtomicReference<Response> response = new AtomicReference<>(chain.proceed(request.get()));
        Set<ResponseInterceptor> responseInterceptors = restClient.getResponseInterceptors();
        responseInterceptors.forEach(it -> response.set(it.intercept(response.get())));
        return response.get();
    }


}
