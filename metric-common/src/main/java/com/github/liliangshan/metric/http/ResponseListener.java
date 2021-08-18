package com.github.liliangshan.metric.http;

/**
 * ResponseListener .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public interface ResponseListener<T>  {

  void onResponse(T resp);

}
