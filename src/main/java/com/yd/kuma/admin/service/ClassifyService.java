package com.yd.kuma.admin.service;

import com.yd.kuma.admin.model.entity.Classify;
import com.yd.kuma.admin.model.vo.ClassifyPagerRequestVO;

import java.util.List;

/**
 * 商品分类业务接口
 *
 * @author gongjiguang
 * @date 2022/9/10
 */
public interface ClassifyService {

    /**
     * 获取分类列表
     *
     * @param classifyPagerRequestVO 分页请求对象
     * @return 分类列表
     */
    List<Classify> getClassifyList(ClassifyPagerRequestVO classifyPagerRequestVO);

    /**
     * 获取分类总数
     *
     * @param status 分类状态
     * @return 分类总数
     */
    int count(Integer status);

    /**
     * 根据id获取分类信息
     *
     * @param id 主键id
     * @return 分类信息
     */
    Classify queryByClassifyId(Integer id);

    /**
     * 根据id判断分类是否存在
     *
     * @param id 主键id
     * @return true 存在 false 不存在
     */
    boolean existByClassifyId(Integer id);

    /**
     * 修改分类
     *
     * @param classify 分类信息
     */
    void update(Classify classify);

    /**
     * 修改分类状态
     *
     * @param classify 分类信息
     */
    void updateStatus(Classify classify);

    /**
     * 创建分类
     *
     * @param classify 分类信息
     * @return 分类信息
     */
    Classify create(Classify classify);
}
