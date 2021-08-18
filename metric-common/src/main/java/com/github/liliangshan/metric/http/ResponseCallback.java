package com.github.liliangshan.metric.http;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * ResponseCallback .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public class ResponseCallback<T> extends ResponseCall<T> implements Callback {

    private final ResponseListener<T> responseListener;

    public ResponseCallback(Class<T> tClass, ResponseListener<T> responseListener) {
        super(tClass);
        this.responseListener = responseListener;
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Request request = call.request();
        logger.error("url:[{}] method:[{}] failure: {}", request.url(), request.method(), e.getMessage());
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        T resp = this.getResponse(call, response);
        if (responseListener != null) {
            responseListener.onResponse(resp);
        }
    }

}
