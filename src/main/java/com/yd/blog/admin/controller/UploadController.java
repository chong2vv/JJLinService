package com.yd.blog.admin.controller;

import com.yd.blog.admin.service.UploadToOssService;
import com.yd.blog.common.constant.enums.ResultErrorEnum;
import com.yd.blog.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 文件上传
 *
 * @author wangyuandong
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
            MultiValueMap<String, MultipartFile> multiFileMap = multipartRequest.getMultiFileMap();

            List<MultipartFile> multipartFileList = multiFileMap.values()
                    .stream()
                    .flatMap(List::stream)
                    .filter(file -> file.getSize() > 0)
                    .collect(Collectors.toList());

            List<String> imgUrlList = uploadToOssService.uploadToOss(multipartFileList);
            return ResultGenerator.genSuccessResult(imgUrlList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genErrorResult(ResultErrorEnum.FILE_ERROR);
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getToken() {
        String token = uploadToOssService.getToken();
        return ResultGenerator.genSuccessResult(token);
    }

}
