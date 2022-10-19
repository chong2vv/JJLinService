package com.yd.kuma.admin.dao;

import com.yd.kuma.admin.model.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author gongjiguang
 * @date 2022-09-03
 */
@Mapper
public interface UserDao {

    /**
     * 查询指定行数据
     *
     * @param offset 开始位置
     * @param count  查询条数
     * @return 对象列表
     */
    List<UserInfo> queryPager(int offset, int count);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(UserInfo user);

    /**
     * 通过用户查询单条数据
     *
     * @param username 手机号
     * @param password 密码
     * @return 实例对象
     */
    UserInfo queryByName(String username, String password);

    /**
     * 通过用户id查询单条数据
     *
     * @param id 用户uid
     * @return 实例对象
     */
    UserInfo queryById(Long id);

    /**
     * 修改数据
     *
     * @param userInfo 实例对象
     * @return 影响行数
     */
    int update(UserInfo userInfo);

    /**
     * 获取用户总数
     *
     * @return 用户总数
     */
    int count();
}

