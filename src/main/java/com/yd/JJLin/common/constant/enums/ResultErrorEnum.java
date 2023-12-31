package com.yd.JJLin.common.constant.enums;

/**
 * 接口返回的错误编码
 *
 * @author wangyuandong
 * @date 2021/11/13
 */
public enum ResultErrorEnum {

    /**
     * 系统错误
     */
    SYSTEM_ERROR(10000, "系统错误"),
    /**
     * 非法用户
     */
    USER_ERROR(10001, "非法用户"),
    /**
     * 参数错误
     */
    PARAM_ERROR(10002, "参数错误"),
    /**
     * 请求不是multipart格式
     */
    MULTIPART_ERROR(10003, "请求不是multipart格式"),
    /**
     * 上传文件失败
     */
    FILE_ERROR(10003, "上传文件失败"),
    /**
     * 非法的手机号
     */
    PHONE_NUMBER_ERROR(10101, "非法的手机号"),
    /**
     * 发送验证码失败
     */
    VERIFICATION_CODE_ERROR(10102, "发送验证码失败"),
    /**
     * 验证失败，请重新获取验证码
     */
    CHECK_VERIFICATION_ERROR(10103, "验证失败，请重新获取验证码"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(10104, "账号或密码错误"),
    /**
     * 该手机以注册
     */
    USERNAME_EXISTS_ERROR(10105, "该用户名以注册"),

    /**
     * 用户被冻结
     */
    STATUS_ERROR(10106, "此用户被冻结"),

    /**
     * 该分类不存在
     */
    CLASSIFY_EXISTS_ERROR(20001, "该分类不存在"),

    /**
     * 该项目不存在
     */
    PROJECT_EXISTS_ERROR(30001, "该项目不存在"),

    /**
     * 导入项目失败
     */
    EXPORT_PROJECT_ERROR(30002, "导入项目失败"),

    /**
     * 项目参数错误
     */
    PROJECT_ARGUMENT_ERROR(30002, "参数错误，不能为空"),

    /**
     * 该blog不存在
     */
    BLOG_EXISTS_ERROR(40001, "该blog不存在"),

    /**
     * 日志产讯参数错误
     */
    BLOG_ARGUMENT_ERROR(40002, "参数错误，不能为空"),

    /**
     * 该日记不存在
     */
    DIARY_EXISTS_ERROR(50001, "该日记不存在"),

    /**
     * 该日记不能为空
     */
    DIARY_EMPTY_ERROR(50002, "该日记不能为空"),

    /**
     * 该日记用户不能为空
     */
    DIARY_UID_ERROR(50003, "用户不能为空"),

    /**
     * tag不存在
     */
    TAG_EXISTS_ERROR(60001, "tag不存在"),

    /**
     * tag已经存在
     */
    TAG_CREATE_EXISTS_ERROR(60002, "tag名已存在"),

    ;

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
