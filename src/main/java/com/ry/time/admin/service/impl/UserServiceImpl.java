package com.ry.time.admin.service.impl;

import com.ry.time.admin.dao.UserDao;
import com.ry.time.admin.model.dto.UserDTO;
import com.ry.time.admin.model.entity.UserInfo;
import com.ry.time.admin.service.UserService;
import com.ry.time.common.model.PagerRequestVO;
import com.ry.time.common.util.CommonUtil;
import com.ry.time.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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

    private String imgUrl;

    @Override
    public List<UserDTO> getUserList(PagerRequestVO pagerRequestVO) {
        pagerRequestVO.initPager();
        List<UserInfo> userInfoList = userDao.queryPager(pagerRequestVO.getOffset(), pagerRequestVO.getCount());
        return userInfoList.stream()
                .map(this::convertUserInfoToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(UserInfo userInfo) {
        userInfo.setId(NumberUtil.genUid());
        if (userInfo.getRoles() == null) {
            userInfo.setRole("admin");
        }else {
            String role = String.join(",", userInfo.getRoles());
            userInfo.setRole(role);
        }
        if (StringUtils.isBlank(userInfo.getAvatar())){
            userInfo.setAvatar(imgUrl + "/default_avatar.png");
        }
        if (userInfo.getStatus() == null){
            userInfo.setStatus(1);
        }
        userDao.insert(userInfo);
    }

    @Override
    public boolean existsUser(String username) {
        UserInfo userInfo = userDao.queryByName(username, null);
        return userInfo != null;
    }

    @Override
    public UserDTO queryByUserName(String username, String password) {
        UserInfo userInfo = userDao.queryByName(username, password);
        if (userInfo == null) {
            return null;
        }
        return convertUserInfoToUserDTO(userInfo);
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
    public int getUserCount() {
        return userDao.count();
    }

    /**
     * 将UserInfo转换为UserDTO
     *
     * @param userInfo 数据库对象
     * @return 页面对象
     */
    private UserDTO convertUserInfoToUserDTO(UserInfo userInfo) {
        UserDTO userDTO = CommonUtil.copyVo(userInfo, UserDTO.class);
        List<String> rolesList = Arrays.asList(userInfo.getRole().split(","));
        userDTO.setRoles(rolesList);
        return userDTO;
    }

    @Value("${img.url}")
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
