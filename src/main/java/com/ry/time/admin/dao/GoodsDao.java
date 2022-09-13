package com.ry.time.admin.dao;

import com.ry.time.admin.model.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 商品(Goods)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-13 17:01:12
 */
public interface GoodsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Goods queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param goods 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Goods> queryAllByLimit(Goods goods, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param goods 查询条件
     * @return 总行数
     */
    long count(Goods goods);

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 影响行数
     */
    int insert(Goods goods);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Goods> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Goods> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Goods> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Goods> entities);

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 影响行数
     */
    int update(Goods goods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

