package com.yd.blog.admin.controller;

import com.yd.blog.admin.model.entity.Classify;
import com.yd.blog.admin.model.vo.ClassifyPagerRequestVO;
import com.yd.blog.admin.service.ClassifyService;
import com.yd.blog.common.constant.enums.ResultErrorEnum;
import com.yd.blog.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目分类
 *
 * @author wangyuandong
 * @date 2022/9/10
 */
@RestController
@RequestMapping("/vue-admin-template/classify/")
@RequiredArgsConstructor
public class ClassifyController {

    private final ClassifyService classifyService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClassifyList(ClassifyPagerRequestVO classifyPagerRequestVO) {
        List<Classify> classifyList = classifyService.getClassifyList(classifyPagerRequestVO);
        int count = classifyService.count(classifyPagerRequestVO.getStatus());
        if (classifyList.isEmpty()) {
            return ResultGenerator.genSuccessPager(null, 0);
        }
        return ResultGenerator.genSuccessPager(classifyList,count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDetail(@RequestParam Integer id) {
        Classify classify = classifyService.queryByClassifyId(id);
        return ResultGenerator.genSuccessResult(classify);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody Classify classify) {
        boolean existClassify = classifyService.existByClassifyId(classify.getId());
        if (!existClassify) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.CLASSIFY_EXISTS_ERROR);
        }
        classifyService.update(classify);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/op", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateStatus(@RequestBody Classify classify) {
        boolean existClassify = classifyService.existByClassifyId(classify.getId());
        if (!existClassify) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.CLASSIFY_EXISTS_ERROR);
        }
        classifyService.updateStatus(classify);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Classify classify) {
        Classify newClassify = classifyService.create(classify);
        return ResultGenerator.genSuccessResult(newClassify);
    }
}
