package com.ry.time.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

/**
 * 操作json的封装方法
 * use:jackson
 *
 * @author gongjiguang
 */
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 001.json转换成对象
     *
     * @param clazz   传入对象，json字符串
     * @param jsonStr json字符串
     * @return 对象T
     */
    public static <T> T jsonToObj(String jsonStr, Class<T> clazz) throws JsonProcessingException {
        if (StringUtils.isBlank(jsonStr) || clazz == null) {
            throw new JsonProcessingException("jsonStr or clazz is null") {};
        }
        return OBJECT_MAPPER.readValue(jsonStr, clazz);
    }

    /**
     * 002.对象转换成json
     *
     * @param obj 传入对象
     * @return json字符串
     */
    public static String objToJson(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }
}