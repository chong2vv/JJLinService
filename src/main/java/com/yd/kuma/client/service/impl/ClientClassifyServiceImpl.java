package com.yd.kuma.client.service.impl;

import com.yd.kuma.admin.dao.ClassifyDao;
import com.yd.kuma.admin.model.entity.Classify;
import com.yd.kuma.admin.model.vo.ClassifyPagerRequestVO;
import com.yd.kuma.client.service.ClientClassifyService;
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

    @Override
    public Classify queryByClassifyId(Integer id) {
        return classifyDao.queryById(id);
    }


}
