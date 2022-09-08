package com.ry.time.admin.service.impl;

import com.ry.time.admin.dao.UserDao;
import com.ry.time.admin.model.dto.UserDTO;
import com.ry.time.admin.model.entity.UserInfo;
import com.ry.time.admin.service.UserService;
import com.ry.time.common.model.PagerRequestVO;
import com.ry.time.common.util.CommonUtil;
import com.ry.time.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserService实现类
 *
 * @author gongjiguang
 * @date 2022/9/2
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public List<UserDTO> getUserList(PagerRequestVO pagerRequestVO) {
        List<UserInfo> userInfoList = userDao.queryPager(pagerRequestVO.getOffset(), pagerRequestVO.getCount());
        return userInfoList.stream()
                .map(this::convertUserInfoToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(UserInfo userInfo) {
        userInfo.setId(NumberUtil.genUid());
        String role = String.join(",", userInfo.getRoles());
        userInfo.setRole(role);
        userDao.insert(userInfo);
    }

    private UserDTO convertUserInfoToUserDTO(UserInfo userInfo) {
        UserDTO userDTO = CommonUtil.copyVo(userInfo, UserDTO.class);
        List<String> rolesList = Arrays.asList(userInfo.getRole().split(","));
        userDTO.setRoles(rolesList);
        return userDTO;
    }
}
