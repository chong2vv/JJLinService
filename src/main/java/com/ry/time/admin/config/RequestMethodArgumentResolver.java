package com.ry.time.admin.config;

import com.ry.time.admin.annotation.RequestAll;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 〈一句话功能简述〉
 *
 * @author gongjiguang
 * @date 2022/9/12
 */
public class RequestMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;
    private ModelAttributeMethodProcessor modelAttributeMethodProcessor;

    public RequestMethodArgumentResolver(ModelAttributeMethodProcessor methodProcessor,
                                       RequestResponseBodyMethodProcessor bodyMethodProcessor) {
        this.modelAttributeMethodProcessor = methodProcessor;
        this.requestResponseBodyMethodProcessor = bodyMethodProcessor;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return modelAttributeMethodProcessor.supportsParameter(parameter) ||
                requestResponseBodyMethodProcessor.supportsParameter(parameter);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest
            , WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request != null) {
            if (HttpMethod.GET.matches(request.getMethod().toUpperCase())) {
                return modelAttributeMethodProcessor.resolveArgument(parameter, mavContainer, webRequest,
                        binderFactory);
            }
            if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
                return requestResponseBodyMethodProcessor.resolveArgument(parameter, mavContainer, webRequest,
                        binderFactory);
            }
        }
        return modelAttributeMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }
}