package com.ry.time.client.service.impl;

import com.ry.time.admin.dao.MessageDao;
import com.ry.time.admin.model.entity.Message;
import com.ry.time.client.service.ClientMessageService;
import com.ry.time.common.util.DateUtil;
import com.ry.time.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * MessageService实现类
 *
 * @author wangyuandong
 * @date 2022/9/17
 *
 **/

@Service
@RequiredArgsConstructor
public class ClientMessageServiceImpl implements ClientMessageService {
    private final MessageDao messageDao;

    @Override
    public int createMessage(Message message) {
        message.setId(NumberUtil.genUid());
        message.setCreateTime(DateUtil.getCurrentDateTimeStr());
        message.setStatus(0);
        return messageDao.insert(message);
    }
}
