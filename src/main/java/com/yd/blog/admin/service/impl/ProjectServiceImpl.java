package com.yd.blog.admin.service.impl;

import com.yd.blog.admin.dao.ProjectDao;
import com.yd.blog.admin.model.dto.ProjectDTO;
import com.yd.blog.admin.model.dto.ProjectExcelDTO;
import com.yd.blog.admin.model.entity.Project;
import com.yd.blog.admin.model.vo.ProjectPagerRequestVO;
import com.yd.blog.admin.service.ClassifyService;
import com.yd.blog.admin.service.ProjectService;
import com.yd.blog.common.util.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ProjectService实现类
 *
 * @author wangyuandong
 * @date 2022/9/13
 */
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao;

    private final ClassifyService classifyService;

    @Override
    public List<ProjectDTO> getProjectList(ProjectPagerRequestVO projectPagerRequestVO) {
        projectPagerRequestVO.initPager();
        List<Project> projectList = projectDao.queryPager(projectPagerRequestVO);
        return projectList.stream()
                .map(this::convertProjectToProjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public int count(Integer status) {
        return projectDao.count(status);
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
    public boolean existByProjectId(Long id) {
        Project project = projectDao.queryById(id);
        return project != null;
    }

    @Override
    public void update(ProjectDTO projectDTO) {
        Project project = convertProjectDtoToProject(projectDTO);
        projectDao.update(project);
    }

    @Override
    public void updateStatus(ProjectDTO projectDTO) {
        Project project = convertProjectDtoToProject(projectDTO);
        projectDao.updateStatus(project);
    }

    @Override
    public ProjectDTO create(ProjectDTO projectDTO) {
        Project project = convertProjectDtoToProject(projectDTO);
        project.setId(NumberUtil.genUid());
        if (projectDTO.getCoverImg() == null) {
            project.setCoverImg("");
        }
        if (projectDTO.getStatus() == null) {
            project.setStatus(1);
        }
        project.setCreateTime(DateUtil.getCurrentDateTimeStr());
        projectDao.insert(project);
        return convertProjectToProjectDTO(project);
    }

    @Override
    public XSSFWorkbook getProjectTemplateExcel() {
        try {
            Resource resource = new DefaultResourceLoader().getResource("classpath:static/project_template.xlsx");
            return new XSSFWorkbook(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public XSSFWorkbook getProjectExcel(String name) {

        return null;
    }

    @Override
    public String getProjectExportExcel(List<ProjectDTO> list) {

        return null;
    }

    @Override
    public void uploadProjectExcel(XSSFWorkbook hssfWorkbook) {
        XSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        ExcelUtil.removeRow(sheet, 0);
        try {
            List<ProjectExcelDTO> excelList = ExcelExportUtil.importList(sheet, ProjectExcelDTO.class);
            List<Project> list = excelList.stream()
                    .map(this::convertProjectExcelDtoToProject)
                    .collect(Collectors.toList());
            projectDao.insertBatch(list);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
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

    private Project convertProjectDtoToProject(ProjectDTO projectDTO) {
        Project project = CommonUtil.copyVo(projectDTO, Project.class);
        project.setImgList(CommonUtil.listToString(projectDTO.getImgList()));
        project.setVideoList(CommonUtil.listToString(projectDTO.getVideoList()));
        project.setTags(CommonUtil.listToString(projectDTO.getTags()));
        return project;
    }

    private Project convertProjectExcelDtoToProject(ProjectExcelDTO projectExcelDTO) {
        Project project = CommonUtil.copyVo(projectExcelDTO, Project.class);
        project.setId(NumberUtil.genUid());
        project.setCreateTime(DateUtil.getCurrentDateTimeStr());
        if (projectExcelDTO.getCoverImg() == null) {
            project.setCoverImg("");
        }
        project.setStatus(1);
        return project;
    }
}
