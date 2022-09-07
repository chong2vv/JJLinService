package com.ry.time.admin.service;

import com.ry.time.admin.model.entity.Message;
import com.ry.time.admin.model.vo.MessagePagerRequestVO;

import java.util.List;

/**
 * 信息相关接口
 *
 * @author gongjiguang
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
}
