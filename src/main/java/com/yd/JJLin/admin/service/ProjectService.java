package com.yd.JJLin.admin.service;

import com.yd.JJLin.admin.model.dto.ProjectDTO;
import com.yd.JJLin.admin.model.vo.ProjectPagerRequestVO;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * 项目业务接口
 *
 * @author wangyuandong
 * @date 2022/9/13
 */
public interface ProjectService {

    /**
     * 分页查询
     *
     * @param projectPagerRequestVO 项目请求参数
     * @return 分页数据
     */
    List<ProjectDTO> getProjectList(ProjectPagerRequestVO projectPagerRequestVO);

    /**
     * 项目数量
     *
     * @param status 状态
     * @return 项目数量
     */
    int count(Integer status);

    /**
     * 项目详情
     *
     * @param id 项目id
     * @return 项目详情
     */
    ProjectDTO queryByProjectId(Long id);

    /**
     * 判断项目是否存在
     *
     * @param id 项目id
     * @return true 存在 false 不存在
     */
    boolean existByProjectId(Long id);

    /**
     * 更新项目
     *
     * @param projectDTO 项目信息
     */
    void update(ProjectDTO projectDTO);

    /**
     * 更新项目状态
     *
     * @param projectDTO 项目
     */
    void updateStatus(ProjectDTO projectDTO);

    /**
     * 创建项目
     *
     * @param projectDTO 项目
     * @return 项目dto
     */
    ProjectDTO create(ProjectDTO projectDTO);

    /**
     * 获取项目的excel
     *
     * @return excel
     */
    XSSFWorkbook getProjectTemplateExcel();

    /**
     * 获取项目的excel文件
     *
     * @param name 文件名称
     * @return excel
     */
    XSSFWorkbook getProjectExcel(String name);

    /**
     * 获取指定项目按模板生成excel
     *
     * @param list 生成的数据
     * @return excel
     */
    String getProjectExportExcel(List<ProjectDTO> list);

    /**
     * 导入项目
     *
     * @param hssfWorkbook 文件
     */
    void uploadProjectExcel(XSSFWorkbook hssfWorkbook);
}
