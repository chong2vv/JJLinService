package com.yd.blog.admin.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 上传oss服务器上
 *
 * @author wangyuandong
 * @date 2022/8/28
 */
public interface UploadService {

    /**
     * 上传到oss
     *
     * @param fileList 上传文件集合
     * @return oss地址
     */
    List<String> uploadFile(List<MultipartFile> fileList);

    /**
     * 获取图片地址
     * @return 地址字符串
     */
    String getImgDomain();

}
