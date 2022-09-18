package com.ry.time.client.service;

import com.ry.time.admin.model.entity.Message;

public interface ClientMessageService {

    /**
     * 创建信息
     *
     * @param message 用户信息
     */
    int createMessage(Message message);
}
