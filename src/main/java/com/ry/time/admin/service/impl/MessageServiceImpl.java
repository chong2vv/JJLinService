package com.ry.time.admin.service.impl;

import com.ry.time.admin.dao.MessageDao;
import com.ry.time.admin.model.entity.Message;
import com.ry.time.admin.model.vo.MessagePagerRequestVO;
import com.ry.time.admin.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MessageService实现
 *
 * @author gongjiguang
 * @date 2022/9/7
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao;

    @Override
    public List<Message> getMessageList(MessagePagerRequestVO messagePagerRequestVO) {
        messagePagerRequestVO.initPager();
        return messageDao.queryAllByLimit(messagePagerRequestVO.getOffset(), messagePagerRequestVO.getCount(),
                messagePagerRequestVO.getSearchString());
    }

    @Override
    public void createMessage(Message message) {
        messageDao.insert(message);
    }

    @Override
    public void updateMessage(Message message) {
        messageDao.update(message);
    }
}
