package com.ry.time.client.service;

import com.ry.time.admin.model.dto.GoodsDTO;
import com.ry.time.client.model.vo.GoodsHomeDTO;

import java.util.List;

/**
 * 客户端商品相关业务
 *
 * @author gongjiguang
 * @date 2022/9/21
 */
public interface ClientGoodsService {

    /**
     * 获取首页商品列表
     *
     * @param classifyId 分类id
     * @return 商品列表
     */
    List<GoodsHomeDTO> getHomeList(Integer classifyId);

    /**
     * 获取首页商品列表
     *
     * @param classifyId 分类id
     * @return 商品列表
     */
    List<GoodsHomeDTO> getList(Integer classifyId);

    /**
     * 商品详情
     *
     * @param id 商品id
     * @return 商品详情
     */
    GoodsHomeDTO queryByGoodsId(Long id);

}
