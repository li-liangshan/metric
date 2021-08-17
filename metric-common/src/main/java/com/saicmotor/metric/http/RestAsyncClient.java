package com.saicmotor.metric.http;

import java.util.Map;

/**
 * RestAsyncClient .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public interface RestAsyncClient {

    <T> void getAsync(String url, Class<T> tClass, ResponseListener<T> response);

    <T> void getAsync(String url, Map<String, String> queryMap, Class<T> tClass, ResponseListener<T> response);

    <T> void getAsync(RequestObject object, Class<T> tClass, ResponseListener<T> response);

    <I, T> void postAsync(String url, I body, Class<T> tClass, ResponseListener<T> response);

    <I, T> void postAsync(String url, I body, Map<String, String> queryMap, Class<T> tClass, ResponseListener<T> response);

    <I, T> void postAsync(RequestObject object, I body, Class<T> tClass, ResponseListener<T> response);

}
