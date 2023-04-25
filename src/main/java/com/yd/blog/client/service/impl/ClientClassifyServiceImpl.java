package com.yd.blog.client.service.impl;

import com.yd.blog.admin.dao.ClassifyDao;
import com.yd.blog.admin.model.entity.Classify;
import com.yd.blog.admin.model.vo.ClassifyPagerRequestVO;
import com.yd.blog.client.service.ClientClassifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyuandong
 */

@Service
@RequiredArgsConstructor
public class ClientClassifyServiceImpl implements ClientClassifyService {
    private final ClassifyDao classifyDao;

    @Override
    public List<Classify> getClassifyList(ClassifyPagerRequestVO classifyPagerRequestVO) {
        classifyPagerRequestVO.initPager();
        classifyPagerRequestVO.setStatus(1);
        return classifyDao.queryPager(classifyPagerRequestVO);
    }

    /**
     * @param classifyPagerRequestVO 分页请求对象
     * @return List
     */
    @Override
    public List<Classify> getHomeClassifyList(ClassifyPagerRequestVO classifyPagerRequestVO) {
        classifyPagerRequestVO.initPager();
        classifyPagerRequestVO.setStatus(1);
        return classifyDao.getClassIfyCounts(classifyPagerRequestVO);
    }

    @Override
    public Classify queryByClassifyId(Integer id) {
        return classifyDao.queryById(id);
    }


}
