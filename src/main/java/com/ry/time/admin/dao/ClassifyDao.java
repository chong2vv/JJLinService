package com.ry.time.admin.dao;

import com.ry.time.admin.model.entity.Classify;
import com.ry.time.admin.model.vo.ClassifyPagerRequestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品分类(Classify)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-10 19:57:13
 */
@Mapper
public interface ClassifyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Classify queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param vo 分类请求信息
     * @return 对象列表
     */
    List<Classify> queryPager(ClassifyPagerRequestVO vo);

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
     * @param classify 实例对象
     * @return 影响行数
     */
    int insert(Classify classify);

    /**
     * 修改数据
     *
     * @param classify 实例对象
     * @return 影响行数
     */
    int update(Classify classify);

    /**
     * 修改分类状态
     *
     * @param classify 实例对象
     * @return 影响行数
     */
    int updateStatus(Classify classify);
}

