package com.yd.kuma.client.service.impl;

import com.yd.kuma.admin.dao.UserDao;
import com.yd.kuma.admin.model.dto.UserDTO;
import com.yd.kuma.admin.model.entity.UserInfo;
import com.yd.kuma.client.service.ClientUserService;
import com.yd.kuma.common.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author: wangyuandong
 * @Date: 2022/10/25 13:37
 */
@Service
@RequiredArgsConstructor
public class ClientUserServiceImpl implements ClientUserService {

    private final UserDao userDao;

    @Override
    public boolean existsUser(String username) {
        UserInfo userInfo = userDao.queryByName(username, null);
        return userInfo != null;
    }

    @Override
    public UserDTO queryByUserId(Long id) {
        UserInfo userInfo = userDao.queryById(id);
        if (userInfo == null) {
            return null;
        }
        return convertUserInfoToUserDTO(userInfo);
    }

    @Override
    public UserDTO queryByUserName(String username, String password) {
        UserInfo userInfo = userDao.queryByName(username, password);
        if (userInfo == null) {
            return null;
        }
        return convertUserInfoToUserDTO(userInfo);
    }

    /**
     * 将UserInfo转换为UserDTO
     *
     * @param userInfo 数据库对象
     * @return 页面对象
     */
    private UserDTO convertUserInfoToUserDTO(UserInfo userInfo) {
        UserDTO userDTO = CommonUtil.copyVo(userInfo, UserDTO.class);
        userDTO.setRoles(CommonUtil.stringsToList(userInfo.getRole()));
        return userDTO;
    }
}
