package com.yd.blog.client.controller;

import com.yd.blog.admin.model.entity.Classify;
import com.yd.blog.admin.model.vo.ClassifyPagerRequestVO;
import com.yd.blog.client.service.ClientClassifyService;
import com.yd.blog.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangyuandong
 */
@RestController
@RequestMapping("/client/classify/")
@RequiredArgsConstructor
public class ClientClassifyController {
    private final ClientClassifyService classifyService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClassifyList(ClassifyPagerRequestVO classifyPagerRequestVO) {
        List<Classify> classifyList = classifyService.getClassifyList(classifyPagerRequestVO);
        if (classifyList.isEmpty()) {
            return ResultGenerator.genSuccessResult(null);
        }
        return ResultGenerator.genSuccessResult(classifyList);
    }

    @RequestMapping(value = "/home_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHomeClassifyList(ClassifyPagerRequestVO classifyPagerRequestVO) {
        List<Classify> classifyList = classifyService.getHomeClassifyList(classifyPagerRequestVO);
        if (classifyList.isEmpty()) {
            return ResultGenerator.genSuccessResult(null);
        }
        return ResultGenerator.genSuccessResult(classifyList);
    }
}
