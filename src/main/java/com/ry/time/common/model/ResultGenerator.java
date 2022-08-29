package com.ry.time.common.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ry.time.common.constant.enums.ResultErrorEnum;
import com.ry.time.common.util.JsonUtil;

/**
 * 响应结果启动类
 *
 * @author gongjiguang
 */
public class ResultGenerator {

    private static final String SYSTEM_ERROR_JSON = "{\"result\":false, \"error_code\":10000,\"error_msg\":\"系统错误\"}";


    public static String genSuccessResult() {
        Result<Boolean> result = new Result<>();
        result.setResult(true);
        try {
            return JsonUtil.objToJson(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return SYSTEM_ERROR_JSON;
        }
    }


    public static <T> String genSuccessResult(T data) {
        Result<T> result = new Result<>();
        result.setResult(data);
        try {
            return JsonUtil.objToJson(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return SYSTEM_ERROR_JSON;
        }
    }


    public static String genErrorResult(ResultErrorEnum resultError) {
        Result<Boolean> result = new Result<>();
        result.setResult(null);
        result.setErrorCode(resultError.getCode());
        result.setErrorMsg(resultError.getMsg());
        try {
            return JsonUtil.objToJson(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return SYSTEM_ERROR_JSON;
        }
    }
}
