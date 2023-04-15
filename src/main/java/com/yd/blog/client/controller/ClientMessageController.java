package com.yd.blog.client.controller;

import com.yd.blog.admin.model.entity.Message;
import com.yd.blog.admin.model.vo.MessagePagerRequestVO;
import com.yd.blog.client.service.ClientMessageService;
import com.yd.blog.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yd.blog.common.constant.enums.ResultErrorEnum.PARAM_ERROR;

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
    public String createMessage(@RequestBody Message message) {
        int status = clientMessageService.createMessage(message);
        if (status == 0) {
            return ResultGenerator.genErrorResult(PARAM_ERROR);
        }
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMessageList(MessagePagerRequestVO messagePagerRequestVO) {
        List<Message> messageList = clientMessageService.getMessageList(messagePagerRequestVO);
        int total = clientMessageService.getMessageCount();
        if (messageList.isEmpty()) {
            return ResultGenerator.genSuccessPager(null, 0);
        }
        return ResultGenerator.genSuccessPager(messageList, total);
    }
}
