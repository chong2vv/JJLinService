package com.ry.time.admin.service;

import com.ry.time.admin.model.dto.UserDTO;
import com.ry.time.admin.model.entity.Classify;
import com.ry.time.admin.model.entity.UserInfo;
import com.ry.time.common.model.PagerRequestVO;
import org.apache.catalina.User;

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

    /**
     * 判断用户是否存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    boolean existsUser(String username);

    /**
     * 通过手机号查询单条数据
     *
     * @param username 账号
     * @param password 密码
     * @return 实例对象
     */
    UserDTO queryByUserName(String username, String password);

    /**
     * 通过手机号查询单条数据
     *
     * @param id 用户uid
     * @return 实例对象
     */
    UserDTO queryByUserId(Long id);

    /**
     * 获取用户总数
     *
     * @return 用户总数
     */
    int getUserCount();

    /**
     * 修改分类
     *
     * @param userInfo 用户信息
     */
    void update(UserInfo userInfo);
}
