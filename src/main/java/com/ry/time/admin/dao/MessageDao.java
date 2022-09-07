package com.ry.time.admin.dao;


import com.ry.time.admin.model.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Message)表数据库访问层
 *
 * @author gongjiguang
 * @date 2022-09-03
 */
@Mapper
public interface MessageDao {

    /**
     * 查询指定行数据
     *
     * @param offset 开始位置
     * @param count  查询条数
     * @param search 关键词
     * @return 对象列表
     */
    List<Message> queryAllByLimit(int offset, int count,String search);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int update(Message message);

}

