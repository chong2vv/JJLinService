package com.yd.JJLin.admin.controller;

import com.yd.JJLin.admin.model.entity.Message;
import com.yd.JJLin.admin.model.entity.Question;
import com.yd.JJLin.admin.model.vo.MessagePagerRequestVO;
import com.yd.JJLin.admin.model.vo.QuestionPagerRequestVO;
import com.yd.JJLin.admin.service.MessageService;
import com.yd.JJLin.admin.service.QuestionService;
import com.yd.JJLin.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vue-admin-template/question/")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getQuestionList(QuestionPagerRequestVO questionPagerRequestVO) {
        List<Question> questionList = questionService.getQuestionList(questionPagerRequestVO);
        int total = questionService.getQuestionCount();
        if (questionList.isEmpty()) {
            return ResultGenerator.genSuccessPager(null, 0);
        }
        return ResultGenerator.genSuccessPager(questionList, total);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createQuestion(@RequestBody Question question) {
        questionService.createQuestion(question);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateQuestion(@RequestBody Question question) {
        questionService.updateQuestion(question);
        return ResultGenerator.genSuccessResult();
    }
}
