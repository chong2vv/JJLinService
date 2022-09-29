package com.ry.time.admin.service.impl;

import com.ry.time.admin.config.MultipartConfig;
import com.ry.time.admin.service.UploadService;
import com.ry.time.common.util.FtpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * UploadToOssService实现
 *
 * @author gongjiguang
 * @date 2020/9/16
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    @Value("${ftp.host}")
    private String host;

    @Value("${ftp.port}")
    private int port;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.image.directory}")
    private String imgDir;

    @Value("${ftp.img.domain}")
    private String imgDomain;

    private final MultipartConfig multipartConfig;

    @Override
    public List<String> uploadFile(List<MultipartFile> fileList) {
        List<String> imgUrlList = new ArrayList<>();
        for (MultipartFile file : fileList) {
            String fileName = getFileName(file);
            try {
                File local = this.uploadFile(file.getInputStream(), multipartConfig.getTempUpload(), fileName);
                this.ftpFile(local, fileName);
                imgUrlList.add(imgDomain + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgUrlList;
    }

    @Override
    public String getImgDomain() {
        return this.imgDomain;
    }

    /**
     * 上传文件
     *
     * @param local 本地文件
     * @param name  文件名
     */
    public void ftpFile(File local, String name) {
        // 将文件ftp上传至文件服务器（主要包含广告图片、flash）
        FtpUtil ftp = getFtp();
        ftp.storeFileToServer(local, name);
    }

    /**
     * 获取FTPUtil对象
     *
     * @return FTPUtil
     */
    private FtpUtil getFtp() {
        // 将文件ftp上传至文件服务器（主要包含广告图片、flash）
        FtpUtil ftp = FtpUtil.getInstance(host, port);
        ftp.connectAndLogin(username, password);
        ftp.changeWorkDirectory(imgDir);
        return ftp;
    }

    /**
     * 获取文件名
     *
     * @param file 文件对象
     * @return 文件名
     */
    private static String getFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String suffix = fileName != null && fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : "";
        return uuid + suffix.toLowerCase();
    }

    /**
     * 生成临时文件
     *
     * @param file     上传文件
     * @param path     文件路径
     * @param fileName 文件名称
     * @throws IOException IO异常
     */
    private File uploadFile(InputStream file, String path, String fileName) throws IOException {
        //将MultipartFile转为file
        File local = new File(path, fileName);
        FileUtils.copyInputStreamToFile(file, local);
        return local;
    }
}
