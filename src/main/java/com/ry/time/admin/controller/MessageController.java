package com.ry.time.admin.controller;

import com.ry.time.admin.model.entity.Message;
import com.ry.time.admin.model.vo.MessagePagerRequestVO;
import com.ry.time.admin.service.MessageService;
import com.ry.time.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 消息相关接口
 *
 * @author gongjiguang
 * @date 2022/9/7
 */
@RestController
@RequestMapping("/vue-admin-template/message/")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserList(@RequestBody MessagePagerRequestVO messagePagerRequestVO) {
        List<Message> messageList = messageService.getMessageList(messagePagerRequestVO);
        return ResultGenerator.genSuccessResult(messageList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody Message message) {
        messageService.createMessage(message);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody Message message) {
        messageService.updateMessage(message);
        return ResultGenerator.genSuccessResult();
    }
}
