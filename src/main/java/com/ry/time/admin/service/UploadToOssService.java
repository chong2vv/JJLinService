package com.ry.time.admin.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * @param fileList 上传文件集合
     * @return oss地址
     */
    List<String> uploadToOss(List<MultipartFile> fileList);

    /**
     * 获取上传token
     *
     * @return token
     */
    String getToken();
}
