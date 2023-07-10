package com.yd.JJLin.client.service.impl;

import com.yd.JJLin.admin.dao.MessageDao;
import com.yd.JJLin.admin.model.entity.Message;
import com.yd.JJLin.admin.model.vo.MessagePagerRequestVO;
import com.yd.JJLin.client.service.ClientMessageService;
import com.yd.JJLin.common.util.DateUtil;
import com.yd.JJLin.common.util.NumberUtil;
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
