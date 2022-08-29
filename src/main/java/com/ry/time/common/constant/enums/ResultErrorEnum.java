package com.ry.time.common.constant.enums;

/**
 * 接口返回的错误编码
 *
 * @author gongjiguang
 * @date 2021/11/13
 */
public enum ResultErrorEnum {

    /**
     * 系统错误
     */
    SYSTEM_ERROR(10000,"系统错误"),
    /**
     * 非法用户
     */
    USER_ERROR(10001,"非法用户"),
    /**
     * 参数错误
     */
    PARAM_ERROR(10002,"参数错误"),
    /**
     * 请求不是multipart格式
     */
    MULTIPART_ERROR(10003,"请求不是multipart格式"),
    /**
     * 非法的手机号
     */
    PHONE_NUMBER_ERROR(10101,"非法的手机号"),
    /**
     * 发送验证码失败
     */
    VERIFICATION_CODE_ERROR(10102,"发送验证码失败"),
    /**
     * 验证失败，请重新获取验证码
     */
    CHECK_VERIFICATION_ERROR(10103,"验证失败，请重新获取验证码"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(10104,"密码错误"),
    /**
     * 该手机以注册
     */
    PHONE_EXISTS_ERROR(10105,"该手机以注册"),

    /**
     * 用户没有权限
     */
    ADMIN_USER_NOT_ROLE(20107,"用户没有权限");

    private final int code;

    private final String msg;

    ResultErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
