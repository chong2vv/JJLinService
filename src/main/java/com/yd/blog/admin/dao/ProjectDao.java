package com.yd.blog.admin.dao;

import com.yd.blog.admin.model.entity.Project;
import com.yd.blog.admin.model.vo.ProjectPagerRequestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 项目(Project)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-13 17:01:12
 */
@Mapper
public interface ProjectDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Project queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param projectPagerRequestVO 项目请求信息
     * @return 对象列表
     */
    List<Project> queryPager(ProjectPagerRequestVO projectPagerRequestVO);

    /**
     * 统计总行数
     *
     * @param status 状态
     * @return 总行数
     */
    int count(Integer status);

    /**
     * 新增数据
     *
     * @param project 实例对象
     * @return 影响行数
     */
    int insert(Project project);

    /**
     * 批量新增数据
     *
     * @param projectList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Project> projectList);

    /**
     * 修改数据
     *
     * @param project 实例对象
     * @return 影响行数
     */
    int update(Project project);

    /**
     * 修改项目状态
     *
     * @param project 实例对象
     * @return 影响行数
     */
    int updateStatus(Project project);
}

