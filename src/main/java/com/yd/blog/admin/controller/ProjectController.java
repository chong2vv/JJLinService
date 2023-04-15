package com.yd.blog.admin.controller;

import com.yd.blog.admin.model.dto.ProjectDTO;
import com.yd.blog.admin.model.vo.ProjectPagerRequestVO;
import com.yd.blog.admin.service.ProjectService;
import com.yd.blog.common.constant.enums.ResultErrorEnum;
import com.yd.blog.common.model.ResultGenerator;
import com.yd.blog.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 项目
 *
 * @author wangyuandong
 * @date 2022/9/13
 */
@RestController
@RequestMapping("/vue-admin-template/project/")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProjectList(@RequestParam Map<String, Object> map) {
        ProjectPagerRequestVO projectPagerRequestVO = JsonUtil.mapToObj(map, ProjectPagerRequestVO.class);
        List<ProjectDTO> classifyList = projectService.getProjectList(projectPagerRequestVO);
        int count = projectService.count(projectPagerRequestVO.getStatus());
        return ResultGenerator.genSuccessPager(classifyList,count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDetail(@RequestParam Long id) {
        ProjectDTO projectDTO = projectService.queryByProjectId(id);
        if (projectDTO == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.PROJECT_EXISTS_ERROR);
        }
        return ResultGenerator.genSuccessResult(projectDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody ProjectDTO projectDTO) {
        boolean existClassify = projectService.existByProjectId(projectDTO.getId());
        if (!existClassify) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.PROJECT_EXISTS_ERROR);
        }
        projectService.update(projectDTO);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/op", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateStatus(@RequestBody ProjectDTO projectDTO) {
        boolean existClassify = projectService.existByProjectId(projectDTO.getId());
        if (!existClassify) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.PROJECT_EXISTS_ERROR);
        }
        projectService.updateStatus(projectDTO);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody ProjectDTO projectDTO) {
        return ResultGenerator.genSuccessResult(projectService.create(projectDTO));
    }

}
