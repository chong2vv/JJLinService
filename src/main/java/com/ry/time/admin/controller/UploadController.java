package com.ry.time.admin.controller;

import com.ry.time.admin.service.UploadToOssService;
import com.ry.time.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;


/**
 * 文件上传
 *
 * @author gongjiguang
 * @date 2022/8/28
 */
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadToOssService uploadToOssService;

    @RequestMapping(value = "/ossFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadOssFile(MultipartHttpServletRequest multipartRequest) {
        try {
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                MultipartFile mf = entity.getValue();
                uploadToOssService.uploadToOss(mf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult();
    }

}
