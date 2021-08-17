package com.saicmotor.metric.http;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.saicmotor.metric.json.JsonUtils;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * AbstractRestClient .
 *
 * @author liliangshan
 * @date 2021/8/17
 */
public class AbstractRestClient implements RestClient {

    private final OkHttpClient okHttpClient;
    private final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    protected AbstractRestClient() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .connectTimeout(3000, TimeUnit.SECONDS)
                .readTimeout(3000, TimeUnit.SECONDS)
                .writeTimeout(3000, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public <T> void getAsync(String url, Class<T> tClass, ResponseListener<T> response) {
        RequestObject object = new RequestObject(url);
        this.getAsync(object, tClass, response);
    }

    @Override
    public <T> void getAsync(String url, Map<String, String> queryMap, Class<T> tClass, ResponseListener<T> response) {
        RequestObject object = new RequestObject(url, queryMap);
        this.getAsync(object, tClass, response);
    }

    @Override
    public <T> void getAsync(RequestObject object, Class<T> tClass, ResponseListener<T> response) {
        Preconditions.checkArgument(response != null, "response listener is null");
        Call call = this.getGetCall(object, tClass);
        call.enqueue(new ResponseCallback<>(tClass, response));
    }

    @Override
    public <I, T> void postAsync(String url, I body, Class<T> tClass, ResponseListener<T> response) {
        RequestObject object = new RequestObject(url);
        this.postAsync(object, body, tClass, response);
    }

    @Override
    public <I, T> void postAsync(String url, I body, Map<String, String> queryMap, Class<T> tClass, ResponseListener<T> response) {
        RequestObject object = new RequestObject(url, queryMap);
        this.postAsync(object, body, tClass, response);
    }

    @Override
    public <I, T> void postAsync(RequestObject object, I body, Class<T> tClass, ResponseListener<T> response) {
        Preconditions.checkArgument(response != null, "response listener is null");
        Call call = this.getPostCall(object, body, tClass);
        call.enqueue(new ResponseCallback<>(tClass, response));
    }

    @Override
    public <T> T getSync(String url, Class<T> tClass) throws Exception {
        RequestObject object = new RequestObject(url);
        return this.getSync(object, tClass);
    }

    @Override
    public <T> T getSync(String url, Map<String, String> queryMap, Class<T> tClass) throws Exception {
        RequestObject object = new RequestObject(url, queryMap);
        return this.getSync(object, tClass);
    }

    @Override
    public <T> T getSync(RequestObject object, Class<T> tClass) throws Exception {
        Call call = this.getGetCall(object, tClass);
        ResponseCallable<T> responseCallable = new ResponseCallable<>(tClass, call);
        return responseCallable.call();
    }

    @Override
    public <I, T> T postSync(String url, I body, Class<T> tClass) throws Exception {
        RequestObject object = new RequestObject(url);
        return this.postSync(object, body, tClass);
    }

    @Override
    public <I, T> T postSync(String url, I body, Map<String, String> queryMap, Class<T> tClass) throws Exception {
        RequestObject object = new RequestObject(url, queryMap);
        return this.postSync(object, body, tClass);
    }

    @Override
    public <I, T> T postSync(RequestObject object, I body, Class<T> tClass) throws Exception {
        Call call = this.getPostCall(object, body, tClass);
        ResponseCallable<T> responseCallable = new ResponseCallable<>(tClass, call);
        return responseCallable.call();
    }

    private <T> Call getGetCall(RequestObject object, Class<T> tClass) {
        Request request = this.getRequestBuilder(object, tClass).get().build();
        return okHttpClient.newCall(request);
    }

    private <T> Request.Builder getRequestBuilder(RequestObject object, Class<T> tClass) {
        this.checkRequestObject(object, tClass);
        HttpUrl httpUrl = HttpUrl.parse(object.getBaseUrl());
        Preconditions.checkArgument(httpUrl != null, "url is null");
        HttpUrl.Builder builder = httpUrl.newBuilder();
        object.getPathSegments().forEach(builder::addPathSegments);
        object.getQueries().forEach(builder::addQueryParameter);
        Headers headers = this.toOKHttp3Headers(object.getHeaders());
        return new Request.Builder().url(builder.build()).headers(headers);
    }

    private <T> void checkRequestObject(RequestObject object, Class<T> tClass) {
        Preconditions.checkArgument(object != null, "request object is null");
        Preconditions.checkArgument(tClass != null, "response object class is null");
        Preconditions.checkArgument(StringUtils.isBlank(object.getBaseUrl()), "base url is null");
        object.setBaseUrl(StringUtils.trim(object.getBaseUrl()));
        if (object.getPathSegments() == null) {
            object.setPathSegments(Lists.newArrayList());
        }
        if (object.getQueries() == null) {
            object.setQueries(Maps.newHashMap());
        }
        if (object.getHeaders() == null) {
            object.setHeaders(new HttpHeaders());
        }
    }

    private Headers toOKHttp3Headers(HttpHeaders httpHeaders) {
        Map<String, String> params = httpHeaders.toMap();
        Headers headers;
        okhttp3.Headers.Builder builder = new okhttp3.Headers.Builder();
        if (params != null && !params.isEmpty()) {
            Iterator<String> iterator = params.keySet().iterator();
            String key;
            while (iterator.hasNext()) {
                key = iterator.next();
                builder.add(key, params.get(key));
            }
        }
        headers = builder.build();
        return headers;
    }

    private <I, T> Call getPostCall(RequestObject object, I body, Class<T> tClass) {
        Request request = this.getRequestBuilder(object, tClass)
                .post(RequestBody.create(JsonUtils.writeValue(body), mediaType))
                .build();
        return okHttpClient.newCall(request);
    }

}
