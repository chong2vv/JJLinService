package com.yd.blog.admin.dao;


import com.yd.blog.admin.model.entity.Message;
import com.yd.blog.admin.model.vo.MessagePagerRequestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Message)表数据库访问层
 *
 * @author wangyuandong
 * @date 2022-09-03
 */
@Mapper
public interface MessageDao {

    /**
     * 查询指定行数据
     *
     * @param messagePagerRequestVO 请求体
     * @return 对象列表
     */
    List<Message> queryAllByLimit(MessagePagerRequestVO messagePagerRequestVO);

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

    /**
     * 获取消息总数
     *
     * @return 消息总数
     */
    int count();

}

