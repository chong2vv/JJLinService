package com.yd.JJLin.admin.controller;

import com.yd.JJLin.admin.model.entity.Message;
import com.yd.JJLin.admin.model.vo.MessagePagerRequestVO;
import com.yd.JJLin.admin.service.MessageService;
import com.yd.JJLin.common.model.ResultGenerator;
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
 * @author wangyuandong
 * @date 2022/9/7
 */
@RestController
@RequestMapping("/vue-admin-template/message/")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMessageList(MessagePagerRequestVO messagePagerRequestVO) {
        List<Message> messageList = messageService.getMessageList(messagePagerRequestVO);
        int total = messageService.getMessageCount();
        if (messageList.isEmpty()) {
            return ResultGenerator.genSuccessPager(null, 0);
        }
        return ResultGenerator.genSuccessPager(messageList, total);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createMessage(@RequestBody Message message) {
        messageService.createMessage(message);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateMessage(@RequestBody Message message) {
        messageService.updateMessage(message);
        return ResultGenerator.genSuccessResult();
    }
}
