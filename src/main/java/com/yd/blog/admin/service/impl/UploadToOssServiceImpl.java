package com.yd.blog.admin.service.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yd.blog.admin.service.UploadToOssService;
import com.yd.blog.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * UploadToOssService实现
 *
 * @author wangyuandong
 * @date 2020/9/16
 */
@Slf4j
@Service
public class UploadToOssServiceImpl implements UploadToOssService {

    /**
     * 桶名
     */
    private String bucketName;
    /**
     * id
     */
    private String accessKey;
    /**
     * secret
     */
    private String secretKey;

    private String imgUrl;


    @Override
    public List<String> uploadToOss(List<MultipartFile> fileList) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huabei());
        // 指定分片上传版本
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);

        String upToken = this.getToken();

        List<String> imgUrlList = new ArrayList<>();
        for (MultipartFile file : fileList) {
            String fileName = getFileName(file);
            try {
                Response response = uploadManager.put(file.getBytes(), fileName, upToken);
                //解析上传成功的结果
                DefaultPutRet defaultPutRet = JsonUtil.jsonToObj(response.bodyString(), DefaultPutRet.class);
                imgUrlList.add(imgUrl + defaultPutRet.key);
            } catch (QiniuException ex) {
                Response r = ex.response;
                log.error(r.toString());
                try {
                    log.error(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            } catch (IOException e) {
                log.error("json error");
            }
        }
        return imgUrlList;
    }

    @Override
    public String getToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucketName);
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
//        return "test/" + uuid + suffix.toLowerCase();
        return "blog/" + uuid + suffix.toLowerCase();
    }

    @Value("${oss.bucket.name}")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Value("${oss.key.id}")
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Value("${oss.key.secret}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Value("${img.url}")
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
