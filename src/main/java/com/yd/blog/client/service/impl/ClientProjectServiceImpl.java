package com.yd.blog.client.service.impl;

import com.yd.blog.admin.dao.ProjectDao;
import com.yd.blog.admin.model.dto.ProjectDTO;
import com.yd.blog.admin.model.entity.Project;
import com.yd.blog.admin.model.vo.ProjectPagerRequestVO;
import com.yd.blog.admin.service.ClassifyService;
import com.yd.blog.client.model.vo.ProjectHomeDTO;
import com.yd.blog.client.service.ClientProjectService;
import com.yd.blog.common.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClientProjectService实现类
 *
 * @author wangyuandong
 * @date 2022/9/21
 */

@Service
@RequiredArgsConstructor
public class ClientProjectServiceImpl implements ClientProjectService {

    private final ProjectDao projectDao;
    private final ClassifyService classifyService;

    @Override
    public List<ProjectHomeDTO> getHomeList(Integer classifyId) {
        ProjectPagerRequestVO projectPagerRequestVO = new ProjectPagerRequestVO();
        projectPagerRequestVO.setPage(1);
        projectPagerRequestVO.setCount(6);
        projectPagerRequestVO.setIsHomeList(1);
        projectPagerRequestVO.setClassifyId(classifyId);
        List<Project> projectList = projectDao.queryPager(projectPagerRequestVO);
        return projectList.stream()
                .map(this::convertProjectToProjectHomeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectHomeDTO> getList(ProjectPagerRequestVO projectPagerRequestVO) {
        projectPagerRequestVO.setStatus(1);
        List<Project> projectList = projectDao.queryPager(projectPagerRequestVO);
        return projectList.stream()
                .map(this::convertProjectToProjectHomeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO queryByProjectId(Long id) {
        Project project = projectDao.queryById(id);
        if (project == null) {
            return null;
        }
        return this.convertProjectToProjectDTO(project);
    }

    @Override
    public int count(Integer status) {
        return projectDao.count(status);
    }


    /**
     * 转换Project为ProjectHomeDTO
     *
     * @param project 项目
     * @return 项目首页
     */
    private ProjectHomeDTO convertProjectToProjectHomeDTO(Project project) {
        ProjectHomeDTO projectHomeDTO = new ProjectHomeDTO();
        projectHomeDTO.setId(project.getId());
        projectHomeDTO.setTitle(project.getTitle());
        projectHomeDTO.setCoverImg(project.getCoverImg());
        projectHomeDTO.setClassifyId(project.getClassifyId());
        projectHomeDTO.setImgList(CommonUtil.stringsToList(project.getImgList()));
        projectHomeDTO.setContent(project.getContent());
        projectHomeDTO.setExcerpt(project.getExcerpt());
        projectHomeDTO.setClassify(classifyService.queryByClassifyId(project.getClassifyId()));
        return projectHomeDTO;
    }

    /**
     * 转换项目实体为项目DTO
     *
     * @param project 项目实体
     * @return 项目DTO
     */
    private ProjectDTO convertProjectToProjectDTO(Project project) {
        ProjectDTO projectDTO = CommonUtil.copyVo(project, ProjectDTO.class);
        projectDTO.setImgList(CommonUtil.stringsToList(project.getImgList()));
        projectDTO.setVideoList(CommonUtil.stringsToList(project.getVideoList()));
        projectDTO.setTags(CommonUtil.stringsToList(project.getTags()));
        projectDTO.setClassify(classifyService.queryByClassifyId(project.getClassifyId()));
        return projectDTO;
    }
}
