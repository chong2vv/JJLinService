package com.ry.time.admin.service;

import com.ry.time.admin.model.dto.UserDTO;
import com.ry.time.admin.model.entity.UserInfo;
import com.ry.time.common.model.PagerRequestVO;

import java.util.List;

/**
 * 用户相关业务
 *
 * @author gongjiguang
 * @date 2022/9/2
 */
public interface UserService {

    /**
     * 获取用户列表
     *
     * @param pagerRequestVO 分页请求对象
     * @return 用户列表
     */
    List<UserDTO> getUserList(PagerRequestVO pagerRequestVO);

    /**
     * 创建用户信息
     *
     * @param userInfo 用户信息
     */
    void createUser(UserInfo userInfo);
}
