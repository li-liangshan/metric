package com.github.liliangshan.metric.http;

import okhttp3.Call;
import okhttp3.Response;

import java.util.concurrent.Callable;

/**
 * ResponseCallable .
 *
 * @author liliangshan
 * @date 2021/8/17
 */
public class ResponseCallable<T> extends ResponseCall<T> implements Callable<T> {

    private final Call call;

    public ResponseCallable(Class<T> tClass, Call call) {
        super(tClass);
        this.call = call;
    }

    @Override
    public T call() throws Exception {
        Response response = call.execute();
        return this.getResponse(call, response);
    }

}
