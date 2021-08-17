package com.saicmotor.metric.http;

import java.util.Map;

/**
 * RestSyncClient .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public interface RestSyncClient {

    <T> T getSync(String url, Class<T> tClass) throws Exception;

    <T> T getSync(String url, Map<String, String> queryMap, Class<T> tClass) throws Exception;

    <T> T getSync(RequestObject object, Class<T> tClass) throws Exception;

    <I, T> T postSync(String url, I body, Class<T> tClass) throws Exception;

    <I, T> T postSync(String url, I body, Map<String, String> queryMap, Class<T> tClass) throws Exception;

    <I, T> T postSync(RequestObject object, I body, Class<T> tClass) throws Exception;

}
