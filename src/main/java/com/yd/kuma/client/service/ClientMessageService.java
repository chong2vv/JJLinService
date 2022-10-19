package com.yd.kuma.client.service;

import com.yd.kuma.admin.model.entity.Message;

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
