package com.yd.kuma.client.service;

import com.yd.kuma.admin.model.entity.Classify;
import com.yd.kuma.admin.model.vo.ClassifyPagerRequestVO;

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
}
