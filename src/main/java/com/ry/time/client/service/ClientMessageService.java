package com.ry.time.client.service;

import com.ry.time.admin.model.entity.Message;

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
}
