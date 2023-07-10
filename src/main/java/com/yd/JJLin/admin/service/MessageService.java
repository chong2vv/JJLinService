package com.yd.JJLin.admin.service;

import com.yd.JJLin.admin.model.entity.Message;
import com.yd.JJLin.admin.model.vo.MessagePagerRequestVO;

import java.util.List;

/**
 * 信息相关接口
 *
 * @author wangyuandong
 * @date 2022/9/7
 */
public interface MessageService {

    /**
     * 获取信息列表
     *
     * @param messagePagerRequestVO 分页请求对象
     * @return 信息列表
     */
    List<Message> getMessageList(MessagePagerRequestVO messagePagerRequestVO);

    /**
     * 创建信息
     *
     * @param message 用户信息
     */
    void createMessage(Message message);

    /**
     * 更新信息
     *
     * @param message 用户信息
     */
    void updateMessage(Message message);

    /**
     * 获取消息总数
     *
     * @return 消息总数
     */
    int getMessageCount();
}
