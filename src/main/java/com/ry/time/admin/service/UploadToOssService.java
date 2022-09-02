package com.ry.time.admin.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传oss服务器上
 *
 * @author gongjiguang
 * @date 2022/8/28
 */
public interface UploadToOssService {

    /**
     * 上传到oss
     *
     * @param file 上传文件
     */
    void uploadToOss(MultipartFile file);

    /**
     * 获取上传token
     *
     * @return token
     */
    String getToken();
}
