package com.yd.kuma.client.service;

import com.yd.kuma.admin.model.dto.UserDTO;

/**
 * @Author: wangyuandong
 * @Date: 2022/10/25 13:36
 */
public interface ClientUserService {
    /**
     * 通过手机号查询单条数据
     *
     * @param username 账号
     * @param password 密码
     * @return 实例对象
     */
    UserDTO queryByUserName(String username, String password);

    /**
     * 判断用户是否存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    boolean existsUser(String username);

    /**
     * 通过id查询单条数据
     *
     * @param id 用户uid
     * @return 实例对象
     */
    UserDTO queryByUserId(Long id);

}

