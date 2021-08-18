package com.github.liliangshan.metric.http;

import com.github.liliangshan.metric.json.JsonUtils;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * ResponseCall .
 *
 * @author liliangshan
 * @date 2021/8/17
 */
abstract class ResponseCall<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final Class<T> tClass;

    protected ResponseCall(Class<T> tClass) {
        this.tClass = tClass;
    }

    protected String getResponseBody(Response response) throws IOException {
        ResponseBody body = response.body();
        String resp = null;
        if (body != null) {
            resp = body.string();
        }
        return resp;
    }

    protected T getResponse(@NotNull Call call, Response response) throws IOException {
        try {
            String resp = this.getResponseBody(response);
            if (logger.isDebugEnabled()) {
                Request request = call.request();
                logger.debug("url:[{}] method:[{}]  response: {}", request.url(), request.method(), resp);
            }
            return JsonUtils.readValue(resp, tClass);
        } catch (Exception e) {
            throw new HttpIOException(e.getMessage(), e);
        }
    }

}
