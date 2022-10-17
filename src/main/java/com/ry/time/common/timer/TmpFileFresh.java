package com.ry.time.common.timer;

import com.ry.time.admin.config.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * 〈一句话功能简述〉
 *
 * @author gongjiguang
 * @date 2022/10/17
 */
@Component
@RequiredArgsConstructor
public class TmpFileFresh {

    private final MultipartConfig multipartConfig;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteTmpFile() {
        String tempUpload = multipartConfig.getTempUpload();
        try {
            FileUtils.cleanDirectory(new File(tempUpload));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
