package com.yd.JJLin.client.controller;

import com.yd.JJLin.admin.model.dto.ProjectArchiveDTO;
import com.yd.JJLin.admin.model.dto.ProjectDTO;
import com.yd.JJLin.admin.model.vo.ProjectPagerRequestVO;
import com.yd.JJLin.client.model.vo.ProjectHomeDTO;
import com.yd.JJLin.client.service.ClientProjectService;
import com.yd.JJLin.common.constant.enums.ResultErrorEnum;
import com.yd.JJLin.common.model.ResultGenerator;
import com.yd.JJLin.common.util.JsonUtil;
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

    @RequestMapping(value = "/year_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getYearList(@RequestParam Map<String, Object> map) {
        ProjectPagerRequestVO projectPagerRequestVO = JsonUtil.mapToObj(map, ProjectPagerRequestVO.class);
        if (projectPagerRequestVO.getYear() == null || projectPagerRequestVO.getMonth() == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.PROJECT_ARGUMENT_ERROR);
        }
        List<ProjectHomeDTO> projectHomeList = clientProjectService.getArchiveByYearMonthList(projectPagerRequestVO);
        return ResultGenerator.genSuccessResult(projectHomeList);
    }

    @RequestMapping(value = "/archive_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getArchiveList() {
        List<ProjectArchiveDTO> list = clientProjectService.groupByYearMonth();
        return ResultGenerator.genSuccessResult(list);
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
