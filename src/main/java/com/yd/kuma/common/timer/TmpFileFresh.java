package com.yd.kuma.common.timer;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉
 *
 * @author gongjiguang
 * @date 2022/10/17
 */
@Component
@RequiredArgsConstructor
public class TmpFileFresh {


    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteTmpFile() {

    }
}
