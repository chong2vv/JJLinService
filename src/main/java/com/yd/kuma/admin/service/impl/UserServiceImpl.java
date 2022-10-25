package com.yd.kuma.admin.service.impl;

import com.yd.kuma.admin.dao.UserDao;
import com.yd.kuma.admin.model.dto.UserDTO;
import com.yd.kuma.admin.model.entity.UserInfo;
import com.yd.kuma.admin.service.UserService;
import com.yd.kuma.common.model.PagerRequestVO;
import com.yd.kuma.common.util.CommonUtil;
import com.yd.kuma.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
        pagerRequestVO.initPager();
        List<UserInfo> userInfoList = userDao.queryPager(pagerRequestVO.getOffset(), pagerRequestVO.getCount());
        return userInfoList.stream()
                .map(this::convertUserInfoToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserInfo createUser(UserInfo userInfo) {
        userInfo.setId(NumberUtil.genUid());
        if (userInfo.getRoles() == null) {
            userInfo.setRole("admin");
        }else {
            String role = String.join(",", userInfo.getRoles());
            userInfo.setRole(role);
        }
        if (StringUtils.isBlank(userInfo.getAvatar())){
            userInfo.setAvatar("http://cdn.gewudi.com/kuma/%E5%A4%B4%E5%83%8F%20%E5%A5%B3%E5%AD%A9.png");
        }
        if (userInfo.getStatus() == null){
            userInfo.setStatus(1);
        }
        userDao.insert(userInfo);
        return userInfo;
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

    @Override
    public void update(UserInfo userInfo) {
        userDao.update(userInfo);
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
