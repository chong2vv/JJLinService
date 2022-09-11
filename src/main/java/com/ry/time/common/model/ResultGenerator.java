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

    private static final String SYSTEM_ERROR_JSON = "{\"result\":false, \"code\":10000,\"msg\":\"系统错误\"}";

    private static final int SUCCESS = 200;

    public static String genSuccessResult() {
        Result<Boolean> result = new Result<>();
        result.setCode(SUCCESS);
        result.setMessage("请求成功");
        result.setData(true);
        try {
            return JsonUtil.objToJson(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return SYSTEM_ERROR_JSON;
        }
    }


    public static <T> String genSuccessResult(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS);
        result.setMessage("请求成功");
        result.setData(data);
        try {
            return JsonUtil.objToJson(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return SYSTEM_ERROR_JSON;
        }
    }

    public static <T> String genSuccessPager(T data, int total) {
        PagerResponseVO<T> pager = new PagerResponseVO<>();
        pager.setCode(SUCCESS);
        pager.setMessage("请求成功");
        pager.setTotalCount(total);
        pager.setData(data);
        try {
            return JsonUtil.objToJson(pager);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return SYSTEM_ERROR_JSON;
        }
    }


    public static String genErrorResult(ResultErrorEnum resultError) {
        Result<Boolean> result = new Result<>();
        result.setData(null);
        result.setCode(resultError.getCode());
        result.setMessage(resultError.getMsg());
        try {
            return JsonUtil.objToJson(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return SYSTEM_ERROR_JSON;
        }
    }
}
