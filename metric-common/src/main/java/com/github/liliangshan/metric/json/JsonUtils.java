package com.github.liliangshan.metric.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

/**
 * JsonUtils .
 *
 * @author liliangshan
 * @date 2021/8/16
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 序列化容错：如果bean上无任何public property，则不会抛错
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 反序列化容错：遇到未知的字段忽略
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // JSON规范: 空字段和null字段都要参与序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @SuppressWarnings("unchecked")
    public static <T> T readValue(String json, Class<T> tClass) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        if (tClass == null) {
            throw new JsonMetricException("json object class is null");
        }
        if (tClass.getSimpleName().equals(String.class.getSimpleName())) {
            return (T) json;
        }
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new JsonMetricException(e.getMessage(), e);
        }
    }

    public static <T> String writeValue(T instance) {
        if (instance == null) {
            return null;
        }
        if (instance instanceof String) {
            return (String) instance;
        }
        try {
            return objectMapper.writeValueAsString(instance);
        } catch (JsonProcessingException e) {
            throw new JsonMetricException(e.getMessage(), e);
        }
    }

}
