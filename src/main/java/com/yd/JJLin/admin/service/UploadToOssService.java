package com.yd.JJLin.admin.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: wangyuandong
 * @Date: 2022/10/19 19:00
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
     -     * 获取上传token
     -     *
     -     * @return token
     */
    String getToken();
}
