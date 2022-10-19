package com.yd.kuma.admin.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * 文件相关配置
 *
 * @author gongjiguang
 * @date 2022/9/20
 */
@Configuration
@Getter
public class MultipartConfig {

    @Value("${location.upload}")
    private String tempUpload;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        File tmpUploadFile = new File(tempUpload);
        // 判断文件夹是否存在
        if (!tmpUploadFile.exists()) {
            tmpUploadFile.mkdirs();
        }
        factory.setLocation(tempUpload);
        return factory.createMultipartConfig();
    }
}
