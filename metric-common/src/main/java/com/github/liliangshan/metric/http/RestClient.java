package com.github.liliangshan.metric.http;


/**
 * RestClient .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public interface RestClient extends RestSyncClient, RestAsyncClient {

    RestClientConfig getConfig();

}
