package com.ry.time.admin.annotation;

import java.lang.annotation.*;

/**
 * 下载Excel注解
 *
 * @author gongjiguang
 */
@Target({ElementType.METHOD})//在方法上起作用
@Retention(RetentionPolicy.RUNTIME) //运行时起作用
@Documented
public @interface DownloadExcel {

    String fileName() default "download.xlsx";

}
