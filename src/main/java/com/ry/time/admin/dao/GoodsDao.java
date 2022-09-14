package com.ry.time.admin.dao;

import com.ry.time.admin.model.entity.Goods;
import com.ry.time.admin.model.vo.GoodsPagerRequestVO;

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
     * @param goodsPagerRequestVO 商品请求信息
     * @return 对象列表
     */
    List<Goods> queryPager(GoodsPagerRequestVO goodsPagerRequestVO);

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
     * @param goods 实例对象
     * @return 影响行数
     */
    int insert(Goods goods);

    /**
     * 批量新增数据
     *
     * @param goodsList 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<Goods> goodsList);

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 影响行数
     */
    int update(Goods goods);

    /**
     * 修改商品状态
     *
     * @param goods 实例对象
     * @return 影响行数
     */
    int updateStatus(Goods goods);
}

