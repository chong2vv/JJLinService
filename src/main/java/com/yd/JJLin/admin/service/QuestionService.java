package com.yd.JJLin.admin.service;

import com.yd.JJLin.admin.model.entity.Message;
import com.yd.JJLin.admin.model.entity.Question;
import com.yd.JJLin.admin.model.vo.MessagePagerRequestVO;
import com.yd.JJLin.admin.model.vo.QuestionPagerRequestVO;

import java.util.List;

public interface QuestionService {
    /**
     * 获取答题列表
     *
     * @param questionPagerRequestVO 分页请求对象
     * @return 信息列表
     */
    List<Question> getQuestionList(QuestionPagerRequestVO questionPagerRequestVO);

    /**
     * 创建答题
     *
     * @param question 答题信息
     */
    void createQuestion(Question question);

    /**
     * 更新答题
     *
     * @param question 答题信息
     */
    void updateQuestion(Question question);

    /**
     * 获取答题总数
     *
     * @return 消息总数
     */
    int getQuestionCount();
}
