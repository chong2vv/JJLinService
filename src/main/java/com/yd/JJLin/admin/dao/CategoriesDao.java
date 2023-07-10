package com.yd.JJLin.admin.dao;

import com.yd.JJLin.admin.model.entity.Categories;
import com.yd.JJLin.admin.model.vo.CategoriesPagerRequestVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 文章分类(Categories)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-15 18:21:06
 */
@Mapper
public interface CategoriesDao {


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Categories queryById(Integer id);


    /**
     * 查询指定行数据
     *
     * @param vo 分类请求信息
     * @return 对象列表
     */
    List<Categories> getCategoryCounts(CategoriesPagerRequestVO vo);

    /**
     * 查询指定行数据
     *
     * @param vo 分类请求信息
     * @return 对象列表
     */
    List<Categories> queryPager(CategoriesPagerRequestVO vo);

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
     * @param categories 实例对象
     * @return 影响行数
     */
    int insert(Categories categories);

    /**
     * 修改数据
     *
     * @param categories 实例对象
     * @return 影响行数
     */
    int update(Categories categories);

    /**
     * 修改分类状态
     *
     * @param categories 实例对象
     * @return 影响行数
     */
    int updateStatus(Categories categories);

}

