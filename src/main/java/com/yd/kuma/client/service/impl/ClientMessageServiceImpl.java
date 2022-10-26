package com.yd.kuma.client.service.impl;

import com.yd.kuma.admin.dao.MessageDao;
import com.yd.kuma.admin.model.entity.Message;
import com.yd.kuma.admin.model.vo.MessagePagerRequestVO;
import com.yd.kuma.client.service.ClientMessageService;
import com.yd.kuma.common.util.DateUtil;
import com.yd.kuma.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        message.setTitle("悄悄话");
        message.setStatus(1);
        return messageDao.insert(message);
    }


    @Override
    public List<Message> getMessageList(MessagePagerRequestVO messagePagerRequestVO) {
        messagePagerRequestVO.initPager();
        return messageDao.queryAllByLimit(messagePagerRequestVO);
    }

    @Override
    public int getMessageCount() {
        return messageDao.count();
    }
}
