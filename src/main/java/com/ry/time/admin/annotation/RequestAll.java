package com.ry.time.admin.annotation;

import java.lang.annotation.*;

/**
 * 请求所有参数注解
 *
 * @author gongjiguang
 * @date 2022/9/12
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestAll {

    /**
     * 参数名称，默认为""，会以参数名为key
     */
    String value() default "";
}
