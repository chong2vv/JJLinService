package com.ry.time.admin.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.ry.time.common.util.JsonUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 〈一句话功能简述〉
 *
 * @author gongjiguang
 * @date 2022/9/12
 */
public class ExtendRequestParamArgumentResolver extends RequestParamMethodArgumentResolver {

    private static final String JSON_BODY_ATTRIBUTE = "JSON_REQUEST_BODY";

    public ExtendRequestParamArgumentResolver(boolean useDefaultResolution) {
        super(useDefaultResolution);
    }

    public ExtendRequestParamArgumentResolver(ConfigurableBeanFactory beanFactory, boolean useDefaultResolution) {
        super(beanFactory, useDefaultResolution);
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        // 尝试 form格式解析
        Object arg = super.resolveName(name, parameter, request);
        // 解析失败时，尝试 JSON 格式解析
        if (arg == null) {
            JsonNode requestBody = getRequestBody(request);
            // JSON 解析失败，返回null
            if (requestBody == null) {
                return null;
            }
            // 正常解析
            JsonNode argValue = requestBody.get(name);
            Class<?> parameterType = parameter.getParameterType();
            if (parameterType.isPrimitive()) {
                arg = parsePrimitive(parameterType.getName(), argValue);
            } else if (isBasicDataTypes(parameterType)) {
                arg = parseBasicTypeWrapper(parameterType, argValue);
            } else {
                arg = JsonUtil.jsonToObj(argValue.toString(), parameterType);
            }
        }
        return arg;
    }

    /**
     * 基本类型解析
     */
    private Object parsePrimitive(String parameterTypeName, Object value) {
        if (value == null) {
            return null;
        }
        final String booleanTypeName = "boolean";
        if (booleanTypeName.equals(parameterTypeName)) {
            return Boolean.valueOf(value.toString());
        }
        final String intTypeName = "int";
        if (intTypeName.equals(parameterTypeName)) {
            return Integer.valueOf(value.toString());
        }
        final String charTypeName = "char";
        if (charTypeName.equals(parameterTypeName)) {
            return value.toString().charAt(0);
        }
        final String shortTypeName = "short";
        if (shortTypeName.equals(parameterTypeName)) {
            return Short.valueOf(value.toString());
        }
        final String longTypeName = "long";
        if (longTypeName.equals(parameterTypeName)) {
            return Long.valueOf(value.toString());
        }
        final String floatTypeName = "float";
        if (floatTypeName.equals(parameterTypeName)) {
            return Float.valueOf(value.toString());
        }
        final String doubleTypeName = "double";
        if (doubleTypeName.equals(parameterTypeName)) {
            return Double.valueOf(value.toString());
        }
        final String byteTypeName = "byte";
        if (byteTypeName.equals(parameterTypeName)) {
            return Byte.valueOf(value.toString());
        }
        return null;
    }

    /**
     * 基本类型包装类型解析
     *
     * @param parameterType
     * @param value
     * @return
     */
    private Object parseBasicTypeWrapper(Class<?> parameterType, Object value) {
        if (Number.class.isAssignableFrom(parameterType)) {
            Number number = (Number) value;
            if (parameterType == Integer.class) {
                return number.intValue();
            } else if (parameterType == Short.class) {
                return number.shortValue();
            } else if (parameterType == Long.class) {
                return number.longValue();
            } else if (parameterType == Float.class) {
                return number.floatValue();
            } else if (parameterType == Double.class) {
                return number.doubleValue();
            } else if (parameterType == Byte.class) {
                return number.byteValue();
            }
        } else if (parameterType == Boolean.class || parameterType == String.class) {
            return value.toString();
        } else if (parameterType == Character.class) {
            return value.toString().charAt(0);
        }
        return null;
    }

    /**
     * 基本数据类型直接返回
     */
    private boolean isBasicDataTypes(Class<?> clazz) {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(String.class);
        classSet.add(Integer.class);
        classSet.add(Long.class);
        classSet.add(Short.class);
        classSet.add(Float.class);
        classSet.add(Double.class);
        classSet.add(Boolean.class);
        classSet.add(Byte.class);
        classSet.add(Character.class);
        return classSet.contains(clazz);
    }

    private JsonNode getRequestBody(NativeWebRequest webRequest) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        // 尝试从Request 中获取 JSONObject
        JsonNode json = (JsonNode) webRequest.getAttribute(JSON_BODY_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
        if (json == null && request != null) {
            try {
                //需要从输入流中获取参数，Json格式数据不能用request.getParameter(name)方式获得
                json = JsonUtil.getJsonParserByInputStream(request.getInputStream());
                webRequest.setAttribute(JSON_BODY_ATTRIBUTE, json, NativeWebRequest.SCOPE_REQUEST);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

}
