package com.ry.time.client.controller;

import com.ry.time.admin.model.entity.Message;
import com.ry.time.client.service.ClientMessageService;
import com.ry.time.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.ry.time.common.constant.enums.ResultErrorEnum.PARAM_ERROR;

/**
 * 客户端消息相关接口
 *
 * @author wangyuandong
 * @date 2022/9/17
 */
@RestController
@RequestMapping("/client/message/")
@RequiredArgsConstructor
public class ClientMessageController {
    private final ClientMessageService clientMessageService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createMessage(@RequestHeader("website") int website, @RequestBody Message message) {
        message.setWebsite(website);
        int status = clientMessageService.createMessage(message);

        if (status == 0) {
            return ResultGenerator.genErrorResult(PARAM_ERROR);
        }
        return ResultGenerator.genSuccessResult();
    }
}
