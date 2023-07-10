package com.yd.JJLin.admin.service.impl;

import com.yd.JJLin.admin.dao.QuestionDao;
import com.yd.JJLin.admin.model.entity.Question;
import com.yd.JJLin.admin.model.vo.QuestionPagerRequestVO;
import com.yd.JJLin.admin.service.QuestionService;
import com.yd.JJLin.common.util.DateUtil;
import com.yd.JJLin.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    /**
     * @param questionPagerRequestVO 分页请求对象
     * @return 答题列表
     */
    @Override
    public List<Question> getQuestionList(QuestionPagerRequestVO questionPagerRequestVO) {
        questionPagerRequestVO.initPager();
        return questionDao.queryAllByLimit(questionPagerRequestVO);
    }

    /**
     * @param question 答题信息
     */
    @Override
    public void createQuestion(Question question) {
        question.setCreateTime(DateUtil.getCurrentDateTimeStr());
        questionDao.insert(question);
    }

    /**
     * @param question 答题信息
     */
    @Override
    public void updateQuestion(Question question) {
        questionDao.update(question);
    }

    /**
     * @return 总数
     */
    @Override
    public int getQuestionCount() {
        return questionDao.count();
    }
}
