package com.yd.JJLin.client.service;

import com.yd.JJLin.admin.model.entity.Classify;
import com.yd.JJLin.admin.model.vo.ClassifyPagerRequestVO;

import java.util.List;

/**
 * @author wangyuandong
 */
public interface ClientClassifyService {
    /**
     * 获取分类列表
     *
     * @param classifyPagerRequestVO 分页请求对象
     * @return 分类列表
     */
    List<Classify> getClassifyList(ClassifyPagerRequestVO classifyPagerRequestVO);

    /**
     * 获取分类列表
     *
     * @param classifyPagerRequestVO 分页请求对象
     * @return 分类列表
     */
    List<Classify> getHomeClassifyList(ClassifyPagerRequestVO classifyPagerRequestVO);

    /**
     * 根据id获取分类信息
     *
     * @param id 主键id
     * @return 分类信息
     */
    Classify queryByClassifyId(Integer id);
}
