package com.ry.time.client.service.impl;

import com.ry.time.admin.dao.ClassifyDao;
import com.ry.time.admin.model.entity.Classify;
import com.ry.time.admin.model.vo.ClassifyPagerRequestVO;
import com.ry.time.client.service.ClientClassifyService;
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
        return classifyDao.queryPager(classifyPagerRequestVO);
    }
}
