package com.yd.JJLin.admin.service.impl;

import com.yd.JJLin.admin.dao.MessageDao;
import com.yd.JJLin.admin.model.entity.Message;
import com.yd.JJLin.admin.model.vo.MessagePagerRequestVO;
import com.yd.JJLin.admin.service.MessageService;
import com.yd.JJLin.common.util.DateUtil;
import com.yd.JJLin.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MessageService实现
 *
 * @author wangyuandong
 * @date 2022/9/7
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao;

    @Override
    public List<Message> getMessageList(MessagePagerRequestVO messagePagerRequestVO) {
        messagePagerRequestVO.initPager();
        return messageDao.queryAllByLimit(messagePagerRequestVO);
    }

    @Override
    public void createMessage(Message message) {
        message.setId(NumberUtil.genUid());
        message.setCreateTime(DateUtil.getCurrentDateTimeStr());
        messageDao.insert(message);
    }

    @Override
    public void updateMessage(Message message) {
        messageDao.update(message);
    }

    @Override
    public int getMessageCount() {
        return messageDao.count();
    }
}
