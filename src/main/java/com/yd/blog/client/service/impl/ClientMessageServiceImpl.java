package com.yd.blog.client.service.impl;

import com.yd.blog.admin.dao.MessageDao;
import com.yd.blog.admin.model.entity.Message;
import com.yd.blog.admin.model.vo.MessagePagerRequestVO;
import com.yd.blog.client.service.ClientMessageService;
import com.yd.blog.common.util.DateUtil;
import com.yd.blog.common.util.NumberUtil;
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
