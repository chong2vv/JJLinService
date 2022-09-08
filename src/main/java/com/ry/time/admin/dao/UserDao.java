package com.ry.time.admin.dao;

import com.ry.time.admin.model.entity.UserInfo;
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

}

