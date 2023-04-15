package com.yd.blog.client.controller;

import com.yd.blog.admin.model.dto.ProjectDTO;
import com.yd.blog.admin.model.vo.ProjectPagerRequestVO;
import com.yd.blog.client.model.vo.ProjectHomeDTO;
import com.yd.blog.client.service.ClientProjectService;
import com.yd.blog.common.constant.enums.ResultErrorEnum;
import com.yd.blog.common.model.ResultGenerator;
import com.yd.blog.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 客户端项目相关接口
 *
 * @author wangyuandong
 * @date 2022/9/21
 */
@RestController
@RequestMapping("/client/project/")
@RequiredArgsConstructor
public class ClientProjectController {

    private final ClientProjectService clientProjectService;

    @RequestMapping(value = "/home/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHomeList(@RequestParam(value = "classify_id", required = false) Integer classifyId) {
        List<ProjectHomeDTO> projectHomeList = clientProjectService.getHomeList(classifyId);
        if (CollectionUtils.isEmpty(projectHomeList)) {
            return ResultGenerator.genSuccessResult(null);
        }
        return ResultGenerator.genSuccessResult(projectHomeList);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getList(@RequestParam Map<String, Object> map) {
        ProjectPagerRequestVO projectPagerRequestVO = JsonUtil.mapToObj(map, ProjectPagerRequestVO.class);
        projectPagerRequestVO.initPager();
        List<ProjectHomeDTO> projectHomeList = clientProjectService.getList(projectPagerRequestVO);
        int count = clientProjectService.count(projectPagerRequestVO.getStatus());
        return ResultGenerator.genSuccessPager(projectHomeList, count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDetail(@RequestParam Long id) {
        ProjectDTO projectDTO = clientProjectService.queryByProjectId(id);
        if (projectDTO == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.PROJECT_EXISTS_ERROR);
        }
        return ResultGenerator.genSuccessResult(projectDTO);
    }
}
