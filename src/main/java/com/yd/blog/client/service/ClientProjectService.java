package com.yd.blog.client.service;

import com.yd.blog.admin.model.dto.ProjectArchiveDTO;
import com.yd.blog.admin.model.dto.ProjectDTO;
import com.yd.blog.admin.model.vo.ProjectPagerRequestVO;
import com.yd.blog.client.model.vo.ProjectHomeDTO;

import java.util.List;

/**
 * 客户端项目相关业务
 *
 * @author wangyuandong
 * @date 2022/9/21
 */
public interface ClientProjectService {

    /**
     * 获取首页项目列表
     *
     * @param classifyId 分类id
     * @return 项目列表
     */
    List<ProjectHomeDTO> getHomeList(Integer classifyId);

    /**
     * 获取首页项目列表
     *
     * @param projectPagerRequestVO 分类id
     * @return 项目列表
     */
    List<ProjectHomeDTO> getList(ProjectPagerRequestVO projectPagerRequestVO);

    /**
     * 按归档年月返回数据
     * @param projectPagerRequestVO 查询条件
     * @return 对象数组
     */
    List<ProjectHomeDTO> getArchiveByYearMonthList(ProjectPagerRequestVO projectPagerRequestVO);

    /**
     * 项目详情
     *
     * @param id 项目id
     * @return 项目详情
     */
    ProjectDTO queryByProjectId(Long id);

    /**
     * 项目归档
     * @return 归档数据
     */
    List<ProjectArchiveDTO> groupByYearMonth();

    /**
     * 项目数量
     *
     * @param status 状态
     * @return 项目数量
     */
    int count(Integer status);

}
