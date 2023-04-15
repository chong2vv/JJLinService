package com.yd.blog.client.service;

import com.yd.blog.admin.model.entity.Message;
import com.yd.blog.admin.model.vo.MessagePagerRequestVO;

import java.util.List;

/**
 * @author wangyuandong
 */
public interface ClientMessageService {

    /**
     * 创建信息
     *
     * @param message 用户信息
     * @return 是否成功
     */
    int createMessage(Message message);

    /**
     * 获取信息列表
     *
     * @param messagePagerRequestVO 分页请求对象
     * @return 信息列表
     */
    List<Message> getMessageList(MessagePagerRequestVO messagePagerRequestVO);

    /**
     * 获取消息总数
     *
     * @return 消息总数
     */
    int getMessageCount();
}
