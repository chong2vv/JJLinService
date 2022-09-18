package com.ry.time.client.service;

import com.ry.time.admin.model.entity.Classify;
import com.ry.time.admin.model.vo.ClassifyPagerRequestVO;

import java.util.List;

public interface ClientClassifyService {
    /**
     * 获取分类列表
     *
     * @param classifyPagerRequestVO 分页请求对象
     * @return 分类列表
     */
    List<Classify> getClassifyList(ClassifyPagerRequestVO classifyPagerRequestVO);
}
