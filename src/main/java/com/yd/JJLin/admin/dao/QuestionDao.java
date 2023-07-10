package com.yd.JJLin.admin.dao;

import com.yd.JJLin.admin.model.entity.Question;
import com.yd.JJLin.admin.model.vo.QuestionPagerRequestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionDao {
    /**
     * 查询指定行数据
     *
     * @param questionPagerRequestVO 请求体
     * @return 对象列表
     */
    List<Question> queryAllByLimit(QuestionPagerRequestVO questionPagerRequestVO);

    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 影响行数
     */
    int insert(Question question);

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 影响行数
     */
    int update(Question question);

    /**
     * 获取消息总数
     *
     * @return 消息总数
     */
    int count();
}
